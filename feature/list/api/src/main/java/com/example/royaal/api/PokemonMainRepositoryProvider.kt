package com.example.royaal.api

import androidx.compose.runtime.compositionLocalOf
import com.example.royaal.domain.PokemonMainRepository

interface PokemonMainRepositoryProvider {

    val pokemonMainRepository: PokemonMainRepository
}

val LocalPokemonMainRepositoryProvider = compositionLocalOf<PokemonMainRepositoryProvider> {
    error("No repository provided for main screen")
}