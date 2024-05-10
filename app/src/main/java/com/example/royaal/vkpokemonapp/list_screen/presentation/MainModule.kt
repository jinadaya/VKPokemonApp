package com.example.royaal.vkpokemonapp.list_screen.presentation

import com.example.royaal.vkpokemonapp.list_screen.data.PokemonMainRepositoryImpl
import com.example.royaal.vkpokemonapp.list_screen.domain.PokemonMainRepository
import dagger.Binds
import dagger.Module

@Module
interface MainModule {

    @Binds
    fun bindMainRepository(impl: PokemonMainRepositoryImpl): PokemonMainRepository
}