package com.example.royaal.api

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.royaal.common.di.FeatureEntry

abstract class DetailsFeatureEntry : FeatureEntry {

    companion object {
        private const val DETAILS_ROUTE = "details_screen"
    }

    final override val featureRoute: String
        get() = "$DETAILS_ROUTE/{id}"

    final override val args: List<NamedNavArgument>
        get() = listOf(
            navArgument("id") {
                type = NavType.IntType
            }
        )

    fun destination(id: Int) = "$DETAILS_ROUTE/$id"
}