package com.example.royaal.vkpokemonapp.list_screen.presentation.composables

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
import com.example.royaal.vkpokemonapp.R
import com.example.royaal.vkpokemonapp.common_ui.ErrorDialog
import com.example.royaal.vkpokemonapp.common_ui.LoadingScreen
import com.example.royaal.vkpokemonapp.list_screen.presentation.ListScreenViewModel
import com.example.royaal.vkpokemonapp.list_screen.presentation.ListScreenWish
import com.example.royaal.vkpokemonapp.utils.LoadingState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListScreenViewModel,
    onPokemonClick: (Int) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val state by viewModel.state.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    AnimatedVisibility(visible = showDialog) {
        ErrorDialog(
            text = stringResource(id = R.string.list_screen_error),
            onDismiss = { showDialog = false },
            onTryAgain = {
                viewModel.consume(ListScreenWish.RequestForNewPokemons)
            }
        )
    }

    when (state.loadingState) {
        LoadingState.Loading -> LoadingScreen(modifier)
        is LoadingState.LoadError -> showDialog = true
        LoadingState.Completed -> SuccessScreen(
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