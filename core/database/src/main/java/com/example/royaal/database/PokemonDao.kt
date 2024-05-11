package com.example.royaal.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.royaal.database.models.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPokemon(pokemon: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPokemonList(pokemons: List<PokemonEntity>)

    @Query("update ${PokemonDatabaseCompanion.POKEMON_DATABASE_NAME} set isOwned=:newStatus where id=:id")
    suspend fun updatePokemonStatus(id: Int, newStatus: Boolean)

    @Delete
    suspend fun deletePokemon(pokemon: PokemonEntity)

    @Query("select * from ${PokemonDatabaseCompanion.POKEMON_DATABASE_NAME}")
    fun getPokemons(): Flow<List<PokemonEntity>>

    @Query("select * from ${PokemonDatabaseCompanion.POKEMON_DATABASE_NAME} where id=:id")
    fun getPokemonById(id: Int): Flow<PokemonEntity?>
}