package com.example.royaal.network.model.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeSprites(
    @SerialName("front_default")
    val default: String,
    @SerialName("front_shiny")
    val shiny: String,
)
