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
import com.example.royaal.foundation.utils.LoadingState
import com.example.royaal.presentation.R
import com.example.royaal.presentation.screen.DetailScreenWish
import com.example.royaal.presentation.screen.DetailsViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun SharedTransitionScope.DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel,
    onBackClick: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val state by viewModel.state.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    AnimatedVisibility(visible = showDialog) {
        com.example.royaal.common.common_ui.ErrorDialog(
            text = stringResource(id = R.string.list_screen_error),
            onDismiss = { showDialog = false },
            onTryAgain = {

            }
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
        LoadingState.Loading -> com.example.royaal.common.common_ui.LoadingScreen()
    }
}