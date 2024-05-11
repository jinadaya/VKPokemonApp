package com.example.royaal.presentation.models

internal data class UiListPokemon(
    val id: Int,
    val name: String,
    val defaultPoster: String?,
    val shinyPoster: String?,
    val isOwned: Boolean,
    val stats: UiListStats,
)
