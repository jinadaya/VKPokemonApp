package com.example.royaal.vkpokemonapp.network.model.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OtherSprites(
    @SerialName("home")
    val homeSprites: HomeSprites,
)