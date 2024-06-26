package com.example.royaal.api

import androidx.navigation.NamedNavArgument
import com.example.royaal.common.di.FeatureEntry

abstract class ListEntry : FeatureEntry {

    final override val featureRoute: String
        get() = "list_route"

    final override val args: List<NamedNavArgument>
        get() = emptyList()

    fun destination() = featureRoute

}