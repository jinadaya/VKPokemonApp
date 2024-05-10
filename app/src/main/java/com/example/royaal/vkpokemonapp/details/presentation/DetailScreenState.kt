package com.example.royaal.vkpokemonapp.details.presentation

import com.example.royaal.vkpokemonapp.models.presentation.UiStats
import com.example.royaal.vkpokemonapp.utils.LoadingState

data class DetailScreenState(
    val loadingState: LoadingState = LoadingState.Loading,
    val id: Int,
    val name: String = "",
    val defaultPoster: String? = null,
    val shinyPoster: String? = null,
    val isOwned: Boolean = false,
    val stats: UiStats = UiStats(emptyList(), emptyMap(), emptyMap())
)
