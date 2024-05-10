package com.example.royaal.vkpokemonapp.details.presentation.composables

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Animatable
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.royaal.vkpokemonapp.R
import com.example.royaal.vkpokemonapp.common_ui.HoldChangeAsyncImage
import com.example.royaal.vkpokemonapp.details.presentation.DetailScreenState
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreenSuccess(
    modifier: Modifier = Modifier,
    state: DetailScreenState,
    onOwnedClick: (Boolean) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onBackClick: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val showShiny = remember { Animatable(0f) }
    val initColor = MaterialTheme.colorScheme.primary
    val targetColor = MaterialTheme.colorScheme.onPrimary
    val iconTint = remember { androidx.compose.animation.Animatable(initColor) }

    Column(
        modifier = modifier
            .displayCutoutPadding()
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HoldChangeAsyncImage(
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
            )
            Box(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    scope.launch {
                        showShiny
                            .animateTo((showShiny.value + 1f) % 2)
                    }
                    scope.launch {
                        iconTint.animateTo(
                            if (iconTint.value == targetColor) initColor else targetColor
                        )
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.StarHalf,
                    contentDescription = null,
                    tint = iconTint.value
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
            modifier = Modifier.padding(top = 16.dp),
            stats = state.stats
        )
        Box(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            onClick = onBackClick
        ) {
            Text(
                text = stringResource(id = R.string.back_to_list),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
    BackHandler {
        onBackClick()
    }
}