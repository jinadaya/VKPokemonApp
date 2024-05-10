package com.example.royaal.vkpokemonapp.list_screen.data

import com.example.royaal.vkpokemonapp.database.PokemonEntity
import com.example.royaal.vkpokemonapp.database.PokemonStats
import com.example.royaal.vkpokemonapp.models.domain.PokemonDTO
import com.example.royaal.vkpokemonapp.models.domain.StatsDTO
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

fun List<PokemonEntity>.toPokemonDtoList() = map { it.toPokemonDTO() }

fun Flow<List<PokemonEntity>>.eachAsDto() = map { it.toPokemonDtoList() }

fun Flow<PokemonEntity?>.asDto() = map { it?.toPokemonDTO() }
