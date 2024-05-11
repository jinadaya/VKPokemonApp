package com.example.royaal.api

import androidx.compose.runtime.compositionLocalOf
import com.example.royaal.domain.PokemonDetailsRepository

interface PokemonDetailsRepositoryProvider {

    val pokemonDetailsRepository: PokemonDetailsRepository
}

val LocalPokemonDetailsRepositoryProvider = compositionLocalOf<PokemonDetailsRepositoryProvider> {
    error("No details repository provided")
}