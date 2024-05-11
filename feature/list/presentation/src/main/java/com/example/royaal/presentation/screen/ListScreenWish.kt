package com.example.royaal.presentation.screen

internal sealed class ListScreenWish {
    data object RequestForNewPokemons : ListScreenWish()
    data class SetPokemonAsOwned(val id: Int, val isOwned: Boolean): ListScreenWish()
}