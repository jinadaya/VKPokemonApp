package com.example.royaal.presentation.model

import com.example.royaal.domain.model.StatsDTO


internal fun StatsDTO.toUiStats() =
    UiStats(
        names = statNames,
        statValues = statValues,
        efforts = statEfforts,
    )