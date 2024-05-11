package com.example.royaal.presentation.di

import com.example.royaal.api.PokemonDetailsRepositoryProvider
import com.example.royaal.common.di.DatabaseProvider
import com.example.royaal.common.di.NetworkProvider
import com.example.royaal.foundation.di.PerFeature
import com.example.royaal.presentation.screen.DetailsViewModel
import dagger.BindsInstance
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        PokemonDetailsRepositoryProvider::class,
        DatabaseProvider::class,
        NetworkProvider::class,
    ]
)
internal interface DetailsComponent {

    @PerFeature
    val detailsViewModel: DetailsViewModel

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance
            @PokemonIdQualifier
            id: Int,
            pokemonDetailsRepositoryProvider: PokemonDetailsRepositoryProvider,
            databaseProvider: DatabaseProvider,
            networkProvider: NetworkProvider,
        ): DetailsComponent
    }
}