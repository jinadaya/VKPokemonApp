package com.example.royaal.vkpokemonapp.details.data

import com.example.royaal.vkpokemonapp.database.PokemonDao
import com.example.royaal.vkpokemonapp.database.PokemonEntity
import com.example.royaal.vkpokemonapp.database.PokemonStats
import com.example.royaal.vkpokemonapp.details.domain.PokemonDetailsRepository
import com.example.royaal.vkpokemonapp.list_screen.data.asDto
import com.example.royaal.vkpokemonapp.models.domain.PokemonDTO
import com.example.royaal.vkpokemonapp.models.domain.StatsDTO
import com.example.royaal.vkpokemonapp.network.PokemonApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class PokemonDetailsRepositoryImpl @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val pokemonApi: PokemonApi
) : PokemonDetailsRepository {

    override fun getPokemonInfo(id: Int): Flow<PokemonDTO> = pokemonDao
        .getPokemonById(id)
        .asDto()
        .mapNotNull {
            it ?: try {
                val response = pokemonApi.getPokemonById(id)
                if (response.body() != null) {
                    val newPokemon = response.body()!!
                    val statNames = newPokemon.stats.map { name -> name.stat.name }
                    val statValues = newPokemon.stats.map { stat -> stat.baseStat }
                    val statEfforts = newPokemon.stats.map { stat -> stat.effort }
                    pokemonDao.addPokemon(
                        PokemonEntity(
                            id = newPokemon.id,
                            name = newPokemon.name,
                            defaultPoster = newPokemon.sprite.others.homeSprites.default,
                            shinyPoster = newPokemon.sprite.others.homeSprites.shiny,
                            isOwned = false,
                            stats = PokemonStats(
                                statNames = statNames,
                                statValues = statNames
                                    .mapIndexed { i, name -> name to statValues[i] }
                                    .toMap(),
                                statEfforts = statNames
                                    .mapIndexed { i, name -> name to statEfforts[i] }
                                    .toMap(),
                            )
                        )
                    )
                    PokemonDTO(
                        id = newPokemon.id,
                        name = newPokemon.name,
                        defaultPoster = newPokemon.sprite.others.homeSprites.default,
                        shinyPoster = newPokemon.sprite.others.homeSprites.shiny,
                        isOwned = false,
                        stats = StatsDTO(
                            statNames = statNames,
                            statValues = statNames
                                .mapIndexed { i, name -> name to statValues[i] }
                                .toMap(),
                            statEfforts = statNames
                                .mapIndexed { i, name -> name to statEfforts[i] }
                                .toMap(),
                        )
                    )
                } else {
                    PokemonDTO.placeholder
                }
            } catch (e: Exception) {
                PokemonDTO.placeholder
            }
        }

    override suspend fun changePokemonStatus(id: Int, isOwned: Boolean) =
        pokemonDao.updatePokemonStatus(id, isOwned)
}