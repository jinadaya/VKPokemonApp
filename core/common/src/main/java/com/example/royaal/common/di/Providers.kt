package com.example.royaal.common.di

import androidx.compose.runtime.compositionLocalOf
import com.example.royaal.database.PokemonDao
import com.example.royaal.network.PokemonApi


interface DatabaseProvider {

    val pokemonDao: PokemonDao
}

val LocalDatabaseProvider = compositionLocalOf<DatabaseProvider> {
    error("No database provided")
}

interface NetworkProvider {

    val pokemonApi: PokemonApi
}

val LocalApiProvider = compositionLocalOf<NetworkProvider> {
    error("No api provided")
}