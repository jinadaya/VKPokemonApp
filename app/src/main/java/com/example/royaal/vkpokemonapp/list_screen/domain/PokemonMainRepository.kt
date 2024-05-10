package com.example.royaal.vkpokemonapp.list_screen.domain

import com.example.royaal.vkpokemonapp.models.domain.PokemonDTO
import kotlinx.coroutines.flow.Flow

interface PokemonMainRepository {

    fun getPokemonList(): Flow<List<PokemonDTO>>

    suspend fun changePokemonStatus(id: Int, isOwned: Boolean)

    suspend fun requestMorePokemons()
}