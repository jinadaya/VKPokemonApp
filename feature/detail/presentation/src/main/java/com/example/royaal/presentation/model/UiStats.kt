package com.example.royaal.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class UiStats(
    val names: List<String>,
    val statValues: Map<String, Int>,
    val efforts: Map<String, Int>,
)
