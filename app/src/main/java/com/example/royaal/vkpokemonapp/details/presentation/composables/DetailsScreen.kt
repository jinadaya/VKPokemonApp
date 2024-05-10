package com.example.royaal.vkpokemonapp.details.presentation.composables

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
import com.example.royaal.vkpokemonapp.details.presentation.DetailScreenWish
import com.example.royaal.vkpokemonapp.details.presentation.DetailsViewModel
import com.example.royaal.vkpokemonapp.utils.LoadingState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel,
    onBackClick: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val state by viewModel.state.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    AnimatedVisibility(visible = showDialog) {
        ErrorDialog(
            text = stringResource(id = R.string.list_screen_error),
            onDismiss = { showDialog = false },
            onTryAgain = { }
        )
    }

    when (state.loadingState) {
        LoadingState.Completed -> DetailsScreenSuccess(
            modifier = modifier,
            state = state,
            animatedVisibilityScope = animatedVisibilityScope,
            onBackClick = onBackClick,
            onOwnedClick = {
                viewModel.consume(DetailScreenWish.SetPokemonNewStatus(it))
            }
        )

        is LoadingState.LoadError -> showDialog = true
        LoadingState.Loading -> LoadingScreen()
    }
}