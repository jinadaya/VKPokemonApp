package com.example.royaal.vkpokemonapp.navigation

import com.example.royaal.api.DetailsFeatureEntry
import com.example.royaal.api.ListEntry
import com.example.royaal.common.di.FeatureEntry
import com.example.royaal.common.di.RouteKey
import com.example.royaal.foundation.di.AppScope
import com.example.royaal.presentation.DetailsFeatureEntryImpl
import com.example.royaal.presentation.ListEntryImpl
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