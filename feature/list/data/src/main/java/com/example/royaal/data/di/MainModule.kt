package com.example.royaal.data.di

import com.example.royaal.data.PokemonMainRepositoryImpl
import com.example.royaal.domain.PokemonMainRepository
import dagger.Binds
import dagger.Module

@Module
interface MainModule {

    @Binds
    fun bindMainRepository(impl: PokemonMainRepositoryImpl): PokemonMainRepository
}