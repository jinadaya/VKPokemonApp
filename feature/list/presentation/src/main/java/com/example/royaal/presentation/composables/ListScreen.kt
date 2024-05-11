package com.example.royaal.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.royaal.presentation.R
import com.example.royaal.presentation.screen.ListScreenViewModel
import com.example.royaal.presentation.screen.ListScreenWish

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun SharedTransitionScope.ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListScreenViewModel,
    onPokemonClick: (Int) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val state by viewModel.state.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    AnimatedVisibility(visible = showDialog) {
        com.example.royaal.common.common_ui.ErrorDialog(
            text = stringResource(id = R.string.list_screen_error),
            onDismiss = { showDialog = false },
            onTryAgain = {
                viewModel.consume(ListScreenWish.RequestForNewPokemons)
            }
        )
    }

    when (state.loadingState) {
        com.example.royaal.foundation.utils.LoadingState.Loading -> com.example.royaal.common.common_ui.LoadingScreen(modifier)
        is com.example.royaal.foundation.utils.LoadingState.LoadError -> showDialog = true
        com.example.royaal.foundation.utils.LoadingState.Completed ->
            SuccessScreen(
                modifier = modifier,
                pokemons = { state.pokemons },
                onOwnedClick = { id, isOwned ->
                    viewModel.consume(
                        ListScreenWish.SetPokemonAsOwned(
                            id = id,
                            isOwned = isOwned
                        )
                    )
                },
                onPokemonClick = onPokemonClick,
                onRequestForNewPokemons = {
                    viewModel.consume(
                        ListScreenWish.RequestForNewPokemons
                    )
                },
                animatedVisibilityScope = animatedVisibilityScope
            )
    }
}