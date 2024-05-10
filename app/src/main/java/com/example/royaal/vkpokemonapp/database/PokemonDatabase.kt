package com.example.royaal.vkpokemonapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

object PokemonDatabaseCompanion {
    const val POKEMON_DATABASE_NAME = "PokemonDatabase"
}

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
}