package com.example.royaal.vkpokemonapp.details.api

import androidx.compose.runtime.compositionLocalOf
import com.example.royaal.vkpokemonapp.details.domain.PokemonDetailsRepository

interface PokemonDetailsRepositoryProvider {

    val pokemonDetailsRepository: PokemonDetailsRepository
}

val LocalPokemonDetailsRepositoryProvider = compositionLocalOf<PokemonDetailsRepositoryProvider> {
    error("No details repository provided")
}