package com.example.royaal.common.di

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

typealias Destinations = Map<Class<out FeatureEntry>, FeatureEntry>

inline fun <reified T : FeatureEntry> Destinations.find(): T =
    this[T::class.java] as T

interface FeatureEntry {

    val selectedIcon: ImageVector
    val unselectedIcon: ImageVector
    val name: String
    val featureRoute: String

    val args: List<NamedNavArgument>

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Composable
    fun SharedTransitionScope.Screen(
        modifier: Modifier,
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry,
        animatedVisibilityScope: AnimatedVisibilityScope,
    )

    @OptIn(ExperimentalSharedTransitionApi::class)
    fun NavGraphBuilder.screen(
        modifier: Modifier = Modifier,
        navController: NavHostController,
        destinations: Destinations,
        sharedScope: SharedTransitionScope,
    ) {
        composable(
            route = featureRoute,
            arguments = args
        ) { backStackEntry ->
            sharedScope.Screen(
                modifier,
                navController,
                destinations,
                backStackEntry,
                animatedVisibilityScope = this
            )
        }
    }
}