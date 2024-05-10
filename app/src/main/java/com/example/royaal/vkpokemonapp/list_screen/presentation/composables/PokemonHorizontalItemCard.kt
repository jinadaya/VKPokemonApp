package com.example.royaal.vkpokemonapp.list_screen.presentation.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreHoriz
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.royaal.vkpokemonapp.common_ui.HoldChangeAsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonHorizontalItemCard(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    name: String,
    id: Int,
    default: String?,
    shiny: String?,
    isOwned: Boolean,
    onOwnedClick: (Boolean) -> Unit,
    onPokemonClick: (Int) -> Unit,
) {
    val showShiny = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val initColor = MaterialTheme.colorScheme.primary
    val targetColor = MaterialTheme.colorScheme.onPrimary
    val iconTint = remember { androidx.compose.animation.Animatable(initColor) }
    val screenHeight = LocalConfiguration.current.screenHeightDp
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height((screenHeight / 2f).dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HoldChangeAsyncImage(
                modifier = Modifier
                    .clickable {
                        onPokemonClick(id)
                    }
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
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium,
                )
                Box(modifier = Modifier.weight(1f))
                Row {
                    IconButton(
                        modifier = Modifier,
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
                    Box(modifier = Modifier.weight(1f))
                    IconButton(onClick = { onPokemonClick(id) }) {
                        Icon(imageVector = Icons.Default.MoreHoriz, contentDescription = null)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun Preview() {
    SharedTransitionLayout {
        NavHost(rememberNavController(), "preview") {
            composable("preview") {
                PokemonHorizontalItemCard(
                    modifier = Modifier,
                    animatedVisibilityScope = this,
                    "Preview",
                    1,
                    null,
                    null,
                    isOwned = true,
                    onOwnedClick = {},
                    onPokemonClick = {}
                )
            }
        }
    }
}