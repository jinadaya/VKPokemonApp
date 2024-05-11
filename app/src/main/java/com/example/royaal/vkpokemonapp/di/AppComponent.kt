package com.example.royaal.vkpokemonapp.di

import android.app.Application
import com.example.royaal.api.PokemonDetailsRepositoryProvider
import com.example.royaal.api.PokemonMainRepositoryProvider
import com.example.royaal.common.di.DatabaseProvider
import com.example.royaal.common.di.Destinations
import com.example.royaal.common.di.NetworkProvider
import com.example.royaal.data.di.DetailsModule
import com.example.royaal.data.di.MainModule
import com.example.royaal.database.di.DatabaseModule
import com.example.royaal.foundation.di.AppScope
import com.example.royaal.network.di.NetworkModule
import com.example.royaal.vkpokemonapp.navigation.NavigationModule
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
    PokemonDetailsRepositoryProvider,
    NetworkProvider,
    DatabaseProvider {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance
            application: Application,
        ): AppComponent
    }

    val destinations: Destinations
}