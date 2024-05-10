package com.example.royaal.vkpokemonapp.list_screen.presentation

import com.example.royaal.vkpokemonapp.models.presentation.UiListPokemon
import com.example.royaal.vkpokemonapp.utils.LoadingState

data class ListScreenState(
    val loadingState: LoadingState = LoadingState.Loading,
    val pokemons: List<UiListPokemon> = emptyList(),
)