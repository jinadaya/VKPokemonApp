package com.example.royaal.vkpokemonapp.network.model.details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Move(
    @SerialName("move")
    val move: MoveX,
)