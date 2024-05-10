package com.example.royaal.vkpokemonapp.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.royaal.vkpokemonapp.list_screen.api.ListEntry
import com.example.royaal.vkpokemonapp.utils.Destinations
import com.example.royaal.vkpokemonapp.utils.find

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    destinations: Destinations,
) {
    SharedTransitionLayout {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = destinations.find<ListEntry>().destination(),
        ) {
            destinations.values.forEach { entry ->
                with(entry) {
                    screen(
                        modifier = Modifier,
                        navController = navController,
                        destinations = destinations,
                        sharedScope = this@SharedTransitionLayout
                    )
                }
            }
        }
    }
}