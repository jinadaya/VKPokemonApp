package com.example.royaal.presentation.di

import com.example.royaal.api.PokemonMainRepositoryProvider
import com.example.royaal.common.di.DatabaseProvider
import com.example.royaal.common.di.NetworkProvider
import com.example.royaal.presentation.screen.ListScreenViewModel
import dagger.Component

@com.example.royaal.foundation.di.PerFeature
@Component(
    dependencies = [
        PokemonMainRepositoryProvider::class,
        NetworkProvider::class,
        DatabaseProvider::class,
    ]
)
internal interface ListComponent {

    @com.example.royaal.foundation.di.PerFeature
    val listViewModel: ListScreenViewModel

    @Component.Factory
    interface Factory {

        fun create(
            repositoryProvider: PokemonMainRepositoryProvider,
            networkProvider: NetworkProvider,
            databaseProvider: DatabaseProvider,
        ): ListComponent
    }
}