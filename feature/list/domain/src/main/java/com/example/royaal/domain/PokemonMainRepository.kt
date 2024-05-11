package com.example.royaal.domain

import kotlinx.coroutines.flow.Flow

interface PokemonMainRepository {

    fun getPokemonList(): Flow<List<PokemonListDTO>>

    suspend fun changePokemonStatus(id: Int, isOwned: Boolean)

    suspend fun requestMorePokemons()
}