package com.example.royaal.vkpokemonapp.di

import android.app.Application
import com.example.royaal.vkpokemonapp.database.DatabaseModule
import com.example.royaal.vkpokemonapp.details.api.PokemonDetailsRepositoryProvider
import com.example.royaal.vkpokemonapp.details.presentation.DetailsModule
import com.example.royaal.vkpokemonapp.list_screen.api.PokemonMainRepositoryProvider
import com.example.royaal.vkpokemonapp.list_screen.presentation.MainModule
import com.example.royaal.vkpokemonapp.navigation.NavigationModule
import com.example.royaal.vkpokemonapp.network.di.NetworkModule
import com.example.royaal.vkpokemonapp.utils.Destinations
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        NetworkModule::class,
        DatabaseModule::class,
        MainModule::class,
        DetailsModule::class,
        NavigationModule::class,
    ]
)
interface AppComponent :
    PokemonMainRepositoryProvider,
    PokemonDetailsRepositoryProvider {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance
            application: Application,
        ): AppComponent
    }

    val destinations: Destinations
}