package com.example.royaal.presentation.models

import com.example.royaal.domain.PokemonListDTO
import com.example.royaal.domain.PokemonListStatsDTO

internal fun PokemonListDTO.toUiPokemon() =
    UiListPokemon(
        name = name,
        id = id,
        isOwned = isOwned,
        defaultPoster = defaultPoster,
        shinyPoster = shinyPoster,
        stats = stats.toUiListStats()
    )

internal fun PokemonListStatsDTO.toUiListStats() = UiListStats(
    names = statNames, values = statValues
)

internal fun List<PokemonListDTO>.toUiPokemonList() = map { it.toUiPokemon() }