package com.example.royaal.network.model.details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    @SerialName("abilities")
    val abilities: List<Ability>,
    @SerialName("id")
    val id: Int,
    @SerialName("moves")
    val moves: List<Move>,
    @SerialName("name")
    val name: String,
    @SerialName("stats")
    val stats: List<Stat>,
    @SerialName("sprites")
    val sprite: Sprites,
)