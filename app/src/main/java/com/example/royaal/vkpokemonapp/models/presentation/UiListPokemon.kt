package com.example.royaal.vkpokemonapp.models.presentation

data class UiListPokemon(
    val id: Int,
    val name: String,
    val defaultPoster: String?,
    val shinyPoster: String?,
    val isOwned: Boolean,
)
