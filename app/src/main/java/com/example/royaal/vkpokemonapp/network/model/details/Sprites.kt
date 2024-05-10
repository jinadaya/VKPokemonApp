package com.example.royaal.vkpokemonapp.network.model.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sprites(
    @SerialName("other")
    val others: OtherSprites,
)