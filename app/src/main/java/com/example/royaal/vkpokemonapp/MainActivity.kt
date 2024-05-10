package com.example.royaal.vkpokemonapp

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.royaal.vkpokemonapp.details.api.LocalPokemonDetailsRepositoryProvider
import com.example.royaal.vkpokemonapp.list_screen.api.LocalPokemonMainRepositoryProvider
import com.example.royaal.vkpokemonapp.navigation.MainNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        val appComponent = (this.application as App).appComponent
        setContent {
            AppTheme(
                dynamicColor = false
            ) {
                val navController = rememberNavController()
                CompositionLocalProvider(
                    LocalPokemonMainRepositoryProvider provides appComponent,
                    LocalPokemonDetailsRepositoryProvider provides appComponent,
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