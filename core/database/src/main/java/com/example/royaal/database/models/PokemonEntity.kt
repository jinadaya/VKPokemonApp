package com.example.royaal.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.royaal.database.PokemonDatabaseCompanion

@Entity(
    tableName = PokemonDatabaseCompanion.POKEMON_DATABASE_NAME
)
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val isOwned: Boolean,
    val defaultPoster: String?,
    val shinyPoster: String?,
    @Embedded
    val stats: PokemonStats,
)