package com.example.royaal.data

import com.example.royaal.database.PokemonDao
import com.example.royaal.database.models.PokemonEntity
import com.example.royaal.database.models.PokemonStats
import com.example.royaal.domain.PokemonListDTO
import com.example.royaal.domain.PokemonMainRepository
import com.example.royaal.network.PokemonApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PokemonMainRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val pokemonDao: PokemonDao,
) : PokemonMainRepository {

    private var currentPokemonListSize: Int = 0

    override fun getPokemonList(): Flow<List<PokemonListDTO>> = pokemonDao.getPokemons().onEach {
        currentPokemonListSize = it.size
    }.eachAsDto()

    override suspend fun changePokemonStatus(id: Int, isOwned: Boolean) =
        pokemonDao.updatePokemonStatus(id, isOwned)

    override suspend fun requestMorePokemons() {
        try {
            val response = pokemonApi.getPokemonList(offset = currentPokemonListSize)
            if (response.body() != null) {
                val pokemonList = response.body()!!.pokemonListItems
                for (pokemon in pokemonList) {
                    try {
                        val pokemonToAdd = pokemonApi.getPokemonByName(pokemon.name)
                        if (pokemonToAdd.body() != null) {
                            val newPokemon = pokemonToAdd.body()!!
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
                        }
                    } catch (e: Exception) {
                        continue
                    }
                }
            }
        } catch (e: Exception) {
            return
        }
    }

}