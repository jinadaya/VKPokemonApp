package com.example.royaal.presentation.models

import androidx.compose.runtime.Immutable

@Immutable
data class UiListStats(
    val names: List<String>,
    val values: Map<String, Int>,
)