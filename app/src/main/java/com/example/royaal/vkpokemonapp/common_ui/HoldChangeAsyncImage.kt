package com.example.royaal.vkpokemonapp.common_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.royaal.vkpokemonapp.R

@Composable
fun HoldChangeAsyncImage(
    modifier: Modifier = Modifier,
    changeFromModel: String?,
    changeToModel: String?,
    showState: () -> Float,
) {
    Box {
        AsyncImage(
            modifier = modifier
                .alpha(
                    (1f - showState())
                        .coerceAtMost(1f)
                        .coerceAtLeast(0f)
                )
                .align(Alignment.Center)
                .aspectRatio(1f, true),
            model = changeFromModel?.trim(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            placeholder = painterResource(id = R.drawable.pokemon_placeholder),
            error = painterResource(id = R.drawable.pokemon_placeholder),
        )
        AsyncImage(
            modifier = Modifier
                .alpha(
                    showState()
                        .coerceAtMost(1f)
                        .coerceAtLeast(0f)
                )
                .align(Alignment.Center)
                .aspectRatio(1f, false),
            model = changeToModel?.trim(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            placeholder = painterResource(id = R.drawable.pokemon_placeholder),
            error = painterResource(id = R.drawable.pokemon_placeholder),
        )
    }
}