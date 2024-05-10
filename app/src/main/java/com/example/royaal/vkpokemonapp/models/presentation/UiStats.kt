package com.example.royaal.vkpokemonapp.models.presentation

import androidx.compose.runtime.Immutable

@Immutable
data class UiStats(
    val names: List<String>,
    val statValues: Map<String, Int>,
    val efforts: Map<String, Int>,
)
