package com.example.royaal.presentation.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.royaal.common.common_ui.HoldChangeAsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun SharedTransitionScope.PokemonVerticalItemCard(
    modifier: Modifier = Modifier,
    name: String,
    default: String?,
    shiny: String?,
    isOwned: Boolean,
    onOwnedClick: (Boolean) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    id: Int
) {
    val showShiny = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val initColor = MaterialTheme.colorScheme.primary
    val targetColor = MaterialTheme.colorScheme.onPrimary
    val iconTint = remember { androidx.compose.animation.Animatable(initColor) }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HoldChangeAsyncImage(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "image-dialog-$id"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .sharedElement(
                        state = rememberSharedContentState(key = "image-$id"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                changeFromModel = default,
                changeToModel = shiny,
                showState = {
                    showShiny.value
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "dialog-$id"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        )
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "text-${name}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    text = name,
                    style = MaterialTheme.typography.headlineSmall,
                )
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { onOwnedClick(!isOwned) }
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = if (isOwned) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(
                    modifier = Modifier,
                    onClick = {
                        scope.launch {
                            showShiny.animateTo((showShiny.value + 1f) % 2)
                        }
                        scope.launch {
                            iconTint.animateTo(
                                if (iconTint.value == initColor) targetColor else initColor
                            )
                        }
                    },
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.AutoMirrored.Filled.StarHalf,
                        contentDescription = null,
                        tint = iconTint.value
                    )
                }
            }
        }
    }
}