package com.example.royaal.vkpokemonapp.database

import android.app.Application
import androidx.room.Room
import com.example.royaal.vkpokemonapp.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @AppScope
    @Provides
    fun providePokemonDatabase(
        app: Application
    ) = Room.databaseBuilder(
        app,
        PokemonDatabase::class.java,
        "pokemon_database"
    ).build()

    @Provides
    fun providePokemonDao(db: PokemonDatabase) = db.pokemonDao()
}