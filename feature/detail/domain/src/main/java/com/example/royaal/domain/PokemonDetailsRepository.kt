package com.example.royaal.domain

import com.example.royaal.domain.model.PokemonDTO
import kotlinx.coroutines.flow.Flow

interface PokemonDetailsRepository {

    fun getPokemonInfo(id: Int): Flow<PokemonDTO>

    suspend fun changePokemonStatus(id: Int, isOwned: Boolean)
}