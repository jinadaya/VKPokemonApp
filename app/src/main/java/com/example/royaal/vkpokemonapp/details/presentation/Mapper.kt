package com.example.royaal.vkpokemonapp.details.presentation

import com.example.royaal.vkpokemonapp.models.domain.StatsDTO
import com.example.royaal.vkpokemonapp.models.presentation.UiStats

fun StatsDTO.toUiStats() = UiStats(
    names = statNames,
    statValues = statValues,
    efforts = statEfforts,
)