package com.example.royaal.vkpokemonapp.details.presentation

import com.example.royaal.vkpokemonapp.details.api.PokemonDetailsRepositoryProvider
import com.example.royaal.vkpokemonapp.di.PerFeature
import com.example.royaal.vkpokemonapp.di.qualifiers.PokemonIdQualifier
import dagger.BindsInstance
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        PokemonDetailsRepositoryProvider::class,
    ]
)
interface DetailsComponent {

    @PerFeature
    val detailsViewModel: DetailsViewModel

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance
            @PokemonIdQualifier
            id: Int,
            pokemonDetailsRepositoryProvider: PokemonDetailsRepositoryProvider
        ): DetailsComponent
    }
}