package com.example.royaal.data

import com.example.royaal.database.models.PokemonEntity
import com.example.royaal.database.models.PokemonStats
import com.example.royaal.domain.PokemonListDTO
import com.example.royaal.domain.PokemonListStatsDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal fun PokemonStats.toStatsDTO() = PokemonListStatsDTO(
    statNames = statNames,
    statValues = statValues,
    statEfforts = statEfforts,
)

internal fun PokemonEntity.toPokemonDTO() = PokemonListDTO(
    name = name,
    id = id,
    defaultPoster = defaultPoster,
    shinyPoster = shinyPoster,
    isOwned = isOwned,
    stats = stats.toStatsDTO()
)

internal fun List<PokemonEntity>.toPokemonDtoList() = map { it.toPokemonDTO() }

internal fun Flow<List<PokemonEntity>>.eachAsDto() = map { it.toPokemonDtoList() }