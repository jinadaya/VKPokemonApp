package com.example.royaal.vkpokemonapp.list_screen.presentation

import com.example.royaal.vkpokemonapp.di.PerFeature
import com.example.royaal.vkpokemonapp.list_screen.api.PokemonMainRepositoryProvider
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        PokemonMainRepositoryProvider::class,
    ]
)
interface ListComponent {

    @PerFeature
    val listViewModel: ListScreenViewModel

    @Component.Factory
    interface Factory {

        fun create(
            repositoryProvider: PokemonMainRepositoryProvider
        ): ListComponent
    }
}