package com.example.royaal.vkpokemonapp.navigation

import com.example.royaal.vkpokemonapp.details.api.DetailsFeatureEntry
import com.example.royaal.vkpokemonapp.details.presentation.DetailsFeatureEntryImpl
import com.example.royaal.vkpokemonapp.di.AppScope
import com.example.royaal.vkpokemonapp.di.RouteKey
import com.example.royaal.vkpokemonapp.list_screen.api.ListEntry
import com.example.royaal.vkpokemonapp.list_screen.presentation.ListEntryImpl
import com.example.royaal.vkpokemonapp.utils.FeatureEntry
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
interface NavigationModule {

    @AppScope
    @RouteKey(ListEntry::class)
    @Binds
    @IntoMap
    fun bindsListDestination(impl: ListEntryImpl): FeatureEntry

    @AppScope
    @RouteKey(DetailsFeatureEntry::class)
    @Binds
    @IntoMap
    fun bindDetailsDestination(impl: DetailsFeatureEntryImpl): FeatureEntry
}