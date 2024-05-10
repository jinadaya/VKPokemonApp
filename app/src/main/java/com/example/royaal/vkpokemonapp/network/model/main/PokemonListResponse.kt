package com.example.royaal.vkpokemonapp.network.model.main


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    @SerialName("count")
    val count: Int,
    @SerialName("results")
    val pokemonListItems: List<PokemonListItem>
)