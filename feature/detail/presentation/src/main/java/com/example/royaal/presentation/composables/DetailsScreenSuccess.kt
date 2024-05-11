package com.example.royaal.presentation.composables

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.royaal.presentation.R
import com.example.royaal.presentation.screen.DetailScreenState
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun SharedTransitionScope.DetailsScreenSuccess(
    modifier: Modifier = Modifier,
    state: DetailScreenState,
    onOwnedClick: (Boolean) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onBackClick: () -> Unit,
) {
    val orientation = LocalConfiguration.current.orientation
    val showShiny = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val initColor = MaterialTheme.colorScheme.primary
    val targetColor = MaterialTheme.colorScheme.onPrimary
    val iconTint = remember { androidx.compose.animation.Animatable(initColor) }

    if (orientation == ORIENTATION_PORTRAIT) {
        Column(
            modifier = modifier
                .displayCutoutPadding()
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            com.example.royaal.common.common_ui.HoldChangeAsyncImage(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "image-${state.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                changeFromModel = state.defaultPoster,
                changeToModel = state.shinyPoster,
                showState = {
                    showShiny.value
                }
            )
            PokemonInfo(
                state = state,
                iconTint = { iconTint.value },
                onShowStateChange = {
                    scope.launch {
                        showShiny.animateTo((showShiny.value + 1f) % 2)
                    }
                    scope.launch {
                        iconTint.animateTo(
                            if (iconTint.value == targetColor) initColor else targetColor
                        )
                    }
                },
                onOwnedClick = onOwnedClick,
                onBackClick = onBackClick,
                animatedVisibilityScope = animatedVisibilityScope,
            )
        }
    } else {
        Row(
            modifier = modifier
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            com.example.royaal.common.common_ui.HoldChangeAsyncImage(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "image-${state.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                changeFromModel = state.defaultPoster,
                changeToModel = state.shinyPoster,
                showState = {
                    showShiny.value
                }
            )
            PokemonInfo(
                state = state,
                iconTint = { iconTint.value },
                onShowStateChange = {
                    scope.launch {
                        showShiny.animateTo(
                            (showShiny.value + 1f) % 2,
                            tween(1000)
                        )
                    }
                    scope.launch {
                        iconTint.animateTo(
                            if (iconTint.value == targetColor) initColor else targetColor,
                            tween(1000)
                        )
                    }
                },
                onOwnedClick = onOwnedClick,
                onBackClick = onBackClick,
                animatedVisibilityScope = animatedVisibilityScope,
            )
        }
    }

    BackHandler {
        onBackClick()
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.PokemonInfo(
    modifier: Modifier = Modifier,
    state: DetailScreenState,
    iconTint: () -> Color,
    onShowStateChange: () -> Unit,
    onOwnedClick: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    var isNotClicked by remember { mutableStateOf(true) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(25)
                )
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                style = MaterialTheme.typography.headlineSmall,
                text = state.name,
                modifier = Modifier.sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "text-${state.name}"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
            )
            Box(modifier = Modifier.weight(1f))
            IconButton(
                onClick = onShowStateChange
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.StarHalf,
                    contentDescription = null,
                    tint = iconTint()
                )
            }
            IconButton(onClick = { onOwnedClick(!state.isOwned) }) {
                Icon(
                    imageVector = if (state.isOwned) Icons.Default.CheckCircle else Icons.Outlined.RadioButtonUnchecked,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        StatBlock(
            modifier = Modifier.padding(top = 16.dp), stats = state.stats
        )
        Box(modifier = Modifier.weight(1f))
        Button(modifier = Modifier.fillMaxWidth(0.8f), onClick = {
            if (isNotClicked) {
                isNotClicked = false
                onBackClick()
            }
        }) {
            Text(
                text = stringResource(id = R.string.back_to_list),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}