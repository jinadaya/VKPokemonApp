package com.example.royaal.vkpokemonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.royaal.api.LocalPokemonDetailsRepositoryProvider
import com.example.royaal.api.LocalPokemonMainRepositoryProvider
import com.example.royaal.common.common_ui.ui.theme.AppTheme
import com.example.royaal.common.di.LocalApiProvider
import com.example.royaal.common.di.LocalDatabaseProvider
import com.example.royaal.vkpokemonapp.navigation.MainNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val appComponent = (this.application as App).appComponent
        setContent {
            AppTheme(
                dynamicColor = false
            ) {
                val navController = rememberNavController()
                CompositionLocalProvider(
                    LocalPokemonMainRepositoryProvider provides appComponent,
                    LocalPokemonDetailsRepositoryProvider provides appComponent,
                    LocalApiProvider provides appComponent,
                    LocalDatabaseProvider provides appComponent,
                ) {
                    MainNavigation(
                        modifier = Modifier.navigationBarsPadding(),
                        navController = navController,
                        destinations = appComponent.destinations
                    )
                }
            }
        }
    }
}