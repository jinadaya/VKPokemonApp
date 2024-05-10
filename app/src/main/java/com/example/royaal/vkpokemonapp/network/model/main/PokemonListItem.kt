package com.example.royaal.vkpokemonapp.network.model.main


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListItem(
    @SerialName("name")
    val name: String,
)