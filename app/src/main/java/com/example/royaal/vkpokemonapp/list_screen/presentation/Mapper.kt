package com.example.royaal.vkpokemonapp.list_screen.presentation

import com.example.royaal.vkpokemonapp.models.domain.PokemonDTO
import com.example.royaal.vkpokemonapp.models.presentation.UiListPokemon

fun PokemonDTO.toUiPokemon() = UiListPokemon(
    name = name,
    id = id,
    isOwned = isOwned,
    defaultPoster = defaultPoster,
    shinyPoster = shinyPoster,
)

fun List<PokemonDTO>.toUiPokemonList() = map { it.toUiPokemon() }