package com.example.royaal.presentation

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
import com.example.royaal.api.DetailsFeatureEntry
import com.example.royaal.api.LocalPokemonMainRepositoryProvider
import com.example.royaal.common.di.DaggerViewModelProvider
import com.example.royaal.common.di.Destinations
import com.example.royaal.common.di.LocalApiProvider
import com.example.royaal.common.di.LocalDatabaseProvider
import com.example.royaal.common.di.find
import com.example.royaal.presentation.composables.ListScreen
import com.example.royaal.presentation.di.DaggerListComponent
import javax.inject.Inject

class ListEntryImpl @Inject constructor() : com.example.royaal.api.ListEntry() {
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
        val networkProvider = LocalApiProvider.current
        val databaseProvider = LocalDatabaseProvider.current
        val listComponent = DaggerListComponent.factory().create(
            repositoryProvider, networkProvider, databaseProvider
        )
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