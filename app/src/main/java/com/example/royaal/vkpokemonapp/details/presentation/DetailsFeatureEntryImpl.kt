package com.example.royaal.vkpokemonapp.details.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.outlined.Details
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.royaal.vkpokemonapp.details.api.DetailsFeatureEntry
import com.example.royaal.vkpokemonapp.details.api.LocalPokemonDetailsRepositoryProvider
import com.example.royaal.vkpokemonapp.details.presentation.composables.DetailsScreen
import com.example.royaal.vkpokemonapp.di.DaggerViewModelProvider
import com.example.royaal.vkpokemonapp.utils.Destinations
import javax.inject.Inject

class DetailsFeatureEntryImpl @Inject constructor() : DetailsFeatureEntry() {

    override val selectedIcon: ImageVector
        get() = Icons.Filled.Details
    override val unselectedIcon: ImageVector
        get() = Icons.Outlined.Details
    override val name: String
        get() = "Details"


    @OptIn(ExperimentalSharedTransitionApi::class)
    @Composable
    override fun SharedTransitionScope.Screen(
        modifier: Modifier,
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry,
        animatedVisibilityScope: AnimatedVisibilityScope
    ) {
        val id = backStackEntry.arguments!!.getInt("id")
        val repositoryProvider = LocalPokemonDetailsRepositoryProvider.current
        val detailsComponent = DaggerDetailsComponent.factory().create(
            id = id, pokemonDetailsRepositoryProvider = repositoryProvider
        )
        val viewModel = DaggerViewModelProvider.daggerViewModel {
            detailsComponent.detailsViewModel
        }
        DetailsScreen(
            modifier = modifier,
            viewModel = viewModel,
            onBackClick = { navController.popBackStack() },
            animatedVisibilityScope = animatedVisibilityScope
        )
    }
}