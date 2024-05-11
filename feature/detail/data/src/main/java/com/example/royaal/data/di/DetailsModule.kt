package com.example.royaal.data.di

import com.example.royaal.data.PokemonDetailsRepositoryImpl
import com.example.royaal.domain.PokemonDetailsRepository
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
interface DetailsModule {

    @Binds
    fun bindDetailsRepository(impl: PokemonDetailsRepositoryImpl): PokemonDetailsRepository
}