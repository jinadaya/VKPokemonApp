package com.example.royaal.vkpokemonapp.database

import kotlinx.serialization.Serializable

@Serializable
data class PokemonStats(
    val statNames: List<String>,
    val statValues: Map<String, Int>,
    val statEfforts: Map<String, Int>,
)
