package com.example.royaal.vkpokemonapp.list_screen.presentation.composables

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.royaal.vkpokemonapp.models.presentation.UiListPokemon

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SuccessScreen(
    modifier: Modifier = Modifier,
    pokemons: () -> List<UiListPokemon>,
    onOwnedClick: (Int, Boolean) -> Unit,
    onPokemonClick: (Int) -> Unit,
    onRequestForNewPokemons: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val scope = rememberCoroutineScope()
    val orientation = LocalConfiguration.current.orientation
    val scrollState = rememberLazyListState()
    val scrollAtEnd by remember {
        derivedStateOf {
            pokemons().size - scrollState.firstVisibleItemIndex < 9
        }
    }
    LaunchedEffect(key1 = scrollAtEnd) {
        if (scrollAtEnd) {
            onRequestForNewPokemons()
        }
    }

    if (orientation == ORIENTATION_PORTRAIT) LazyColumn(
        modifier = modifier, contentPadding = PaddingValues(top = 32.dp), state = scrollState
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
                    .clickable {
                        onPokemonClick(pokemon.id)
                    },
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
    else LazyColumn(
        state = scrollState,
    ) {
        items(
            count = pokemons().size / 2,
            key = {
                pokemons()[it].id
            }, contentType = {
                pokemons()[it]
            }
        ) { index ->

            val curr = 2 * index
            val next = curr + 1
            val nextPokemon = pokemons()[next]
            val prevPokemon = pokemons()[curr]
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PokemonHorizontalItemCard(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .clickable {
                            onPokemonClick(prevPokemon.id)
                        },
                    name = prevPokemon.name,
                    id = prevPokemon.id,
                    shiny = prevPokemon.shinyPoster,
                    default = prevPokemon.defaultPoster,
                    isOwned = prevPokemon.isOwned,
                    onOwnedClick = { isOwned ->
                        onOwnedClick(prevPokemon.id, isOwned)
                    },
                    onPokemonClick = onPokemonClick,
                    animatedVisibilityScope = animatedVisibilityScope,
                )
                PokemonHorizontalItemCard(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .clickable {
                            onPokemonClick(nextPokemon.id)
                        },
                    name = nextPokemon.name,
                    id = nextPokemon.id,
                    shiny = nextPokemon.shinyPoster,
                    default = nextPokemon.defaultPoster,
                    isOwned = nextPokemon.isOwned,
                    onOwnedClick = { isOwned ->
                        onOwnedClick(nextPokemon.id, isOwned)
                    },
                    onPokemonClick = onPokemonClick,
                    animatedVisibilityScope = animatedVisibilityScope,
                )
            }
        }
    }
}