package com.example.royaal.presentation.screen

import com.example.royaal.foundation.utils.LoadingState
import com.example.royaal.presentation.models.UiListPokemon

internal data class ListScreenState(
    val loadingState: LoadingState = LoadingState.Loading,
    val pokemons: List<UiListPokemon> = emptyList(),
)