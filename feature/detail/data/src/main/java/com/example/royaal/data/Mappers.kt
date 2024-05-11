package com.example.royaal.data

import com.example.royaal.database.models.PokemonEntity
import com.example.royaal.database.models.PokemonStats
import com.example.royaal.domain.model.PokemonDTO
import com.example.royaal.domain.model.StatsDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun PokemonStats.toStatsDTO() = StatsDTO(
    statNames = statNames,
    statValues = statValues,
    statEfforts = statEfforts,
)

fun PokemonEntity.toPokemonDTO() = PokemonDTO(
    name = name,
    id = id,
    defaultPoster = defaultPoster,
    shinyPoster = shinyPoster,
    isOwned = isOwned,
    stats = stats.toStatsDTO()
)

fun Flow<PokemonEntity?>.asDto() = map { it?.toPokemonDTO() }