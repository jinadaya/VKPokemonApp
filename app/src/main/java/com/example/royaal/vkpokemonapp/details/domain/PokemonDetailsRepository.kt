package com.example.royaal.vkpokemonapp.details.domain

import com.example.royaal.vkpokemonapp.models.domain.PokemonDTO
import kotlinx.coroutines.flow.Flow

interface PokemonDetailsRepository {

    fun getPokemonInfo(id: Int): Flow<PokemonDTO>

    suspend fun changePokemonStatus(id: Int, isOwned: Boolean)
}