package com.example.royaal.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.royaal.foundation.utils.LoadingState
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
        LoadingState.Loading -> com.example.royaal.common.common_ui.LoadingScreen(modifier)
        is LoadingState.LoadError -> showDialog = true
        LoadingState.Completed ->
            SuccessScreen(
                modifier = modifier.safeContentPadding(),
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