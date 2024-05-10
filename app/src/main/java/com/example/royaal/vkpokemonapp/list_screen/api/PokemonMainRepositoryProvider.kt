package com.example.royaal.vkpokemonapp.list_screen.api

import androidx.compose.runtime.compositionLocalOf
import com.example.royaal.vkpokemonapp.list_screen.domain.PokemonMainRepository

interface PokemonMainRepositoryProvider {

    val pokemonMainRepository: PokemonMainRepository
}

val LocalPokemonMainRepositoryProvider = compositionLocalOf<PokemonMainRepositoryProvider> {
    error("No repository provided for main screen")
}