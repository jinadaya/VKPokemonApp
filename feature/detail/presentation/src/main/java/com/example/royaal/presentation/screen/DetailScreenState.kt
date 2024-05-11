package com.example.royaal.presentation.screen

import com.example.royaal.foundation.utils.LoadingState
import com.example.royaal.presentation.model.UiStats

internal data class DetailScreenState(
    val loadingState: LoadingState = LoadingState.Loading,
    val id: Int,
    val name: String = "",
    val defaultPoster: String? = null,
    val shinyPoster: String? = null,
    val isOwned: Boolean = false,
    val stats: UiStats = UiStats(
        emptyList(),
        emptyMap(),
        emptyMap()
    )
)
