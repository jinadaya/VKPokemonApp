package com.example.royaal.vkpokemonapp.details.presentation

import com.example.royaal.vkpokemonapp.details.data.PokemonDetailsRepositoryImpl
import com.example.royaal.vkpokemonapp.details.domain.PokemonDetailsRepository
import dagger.Binds
import dagger.Module

@Module
interface DetailsModule {

    @Binds
    fun bindDetailsRepository(impl: PokemonDetailsRepositoryImpl): PokemonDetailsRepository
}