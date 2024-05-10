package com.example.royaal.vkpokemonapp.list_screen.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.royaal.vkpokemonapp.details.api.DetailsFeatureEntry
import com.example.royaal.vkpokemonapp.di.DaggerViewModelProvider
import com.example.royaal.vkpokemonapp.list_screen.api.ListEntry
import com.example.royaal.vkpokemonapp.list_screen.api.LocalPokemonMainRepositoryProvider
import com.example.royaal.vkpokemonapp.list_screen.presentation.composables.ListScreen
import com.example.royaal.vkpokemonapp.utils.Destinations
import com.example.royaal.vkpokemonapp.utils.find
import javax.inject.Inject

class ListEntryImpl @Inject constructor() : ListEntry() {
    override val selectedIcon: ImageVector
        get() = Icons.Filled.Home
    override val unselectedIcon: ImageVector
        get() = Icons.Outlined.Home
    override val name: String
        get() = "Home"


    @OptIn(ExperimentalSharedTransitionApi::class)
    @Composable
    override fun SharedTransitionScope.Screen(
        modifier: Modifier,
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry,
        animatedVisibilityScope: AnimatedVisibilityScope
    ) {
        val repositoryProvider = LocalPokemonMainRepositoryProvider.current
        val listComponent = DaggerListComponent.factory().create(repositoryProvider)
        val viewModel = DaggerViewModelProvider.daggerViewModel {
            listComponent.listViewModel
        }
        ListScreen(
            modifier = modifier,
            viewModel = viewModel,
            animatedVisibilityScope = animatedVisibilityScope,
            onPokemonClick = {
                val destination = destinations
                    .find<DetailsFeatureEntry>()
                    .destination(it)
                navController.navigate(destination)
            }
        )
    }
}