package com.example.royaal.domain

data class PokemonListDTO(
    val name: String,
    val id: Int,
    val defaultPoster: String?,
    val shinyPoster: String?,
    val isOwned: Boolean,
    val stats: PokemonListStatsDTO,
)