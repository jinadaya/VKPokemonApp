package com.example.royaal.presentation.composables

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.royaal.presentation.R
import com.example.royaal.presentation.models.UiListPokemon

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalFoundationApi::class)
@Composable
internal fun SharedTransitionScope.SuccessScreen(
    modifier: Modifier = Modifier,
    pokemons: () -> List<UiListPokemon>,
    onOwnedClick: (Int, Boolean) -> Unit,
    onPokemonClick: (Int) -> Unit,
    onRequestForNewPokemons: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    var showStatsDialog by remember { mutableStateOf(false) }
    var selectedPokemon by remember { mutableIntStateOf(0) }
    val orientation = LocalConfiguration.current.orientation
    val scrollState = rememberLazyListState()
    val scrollAtEnd by remember {
        derivedStateOf {
            pokemons().size - scrollState.firstVisibleItemIndex < 9 ||
                    scrollState.canScrollForward.not()
        }
    }
    LaunchedEffect(key1 = scrollAtEnd) {
        if (scrollAtEnd) {
            onRequestForNewPokemons()
        }
    }

    AnimatedVisibility(
        visible = showStatsDialog,
        enter = slideInVertically(
            spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessLow
            )
        ),
        exit = slideOutVertically(
            spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    ) {
        val pokemon = pokemons()[selectedPokemon]
        StatsDialog(
            default = pokemon.defaultPoster,
            stats = pokemon.stats,
            name = pokemon.name,
            onDismiss = {
                showStatsDialog = false
            },
        )
    }

    if (pokemons().isEmpty()) Button(
        modifier = Modifier.fillMaxWidth(0.8f), onClick = onRequestForNewPokemons
    ) {
        Text(text = stringResource(id = R.string.request_new_pokemons))
    } else if (orientation == ORIENTATION_PORTRAIT)
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(top = 32.dp),
            state = scrollState
        ) {
            items(count = pokemons().size, key = {
                pokemons()[it].id
            }, contentType = {
                pokemons()[it]
            }) { index ->
                val pokemon = pokemons()[index]
                PokemonVerticalItemCard(
                    modifier = Modifier
                        .padding(4.dp)
                        .combinedClickable(onLongClick = {
                            selectedPokemon = index
                            showStatsDialog = true
                        }, onClick = {
                            onPokemonClick(pokemon.id)
                        }),
                    name = pokemon.name,
                    id = pokemon.id,
                    default = pokemon.defaultPoster,
                    shiny = pokemon.shinyPoster,
                    isOwned = pokemon.isOwned,
                    onOwnedClick = { isOwned ->
                        onOwnedClick(pokemon.id, isOwned)
                    },
                    animatedVisibilityScope = animatedVisibilityScope,
                )
            }
        }
    else
        LazyColumn(
            modifier = modifier,
            state = scrollState,
        ) {
            items(count = pokemons().size / 2, key = {
                pokemons()[it].id
            }, contentType = {
                pokemons()[it]
            }) { index ->
                val pokemon = pokemons()[index]
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    PokemonHorizontalItemCard(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .clickable {
                                onPokemonClick(pokemon.id)
                            },
                        name = pokemon.name,
                        id = pokemon.id,
                        shiny = pokemon.shinyPoster,
                        default = pokemon.defaultPoster,
                        isOwned = pokemon.isOwned,
                        onOwnedClick = { isOwned ->
                            onOwnedClick(pokemon.id, isOwned)
                        },
                        onPokemonClick = onPokemonClick,
                        animatedVisibilityScope = animatedVisibilityScope,
                        stats = pokemon.stats,
                    )
                }
            }
        }
}