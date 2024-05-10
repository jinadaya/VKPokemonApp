package com.example.royaal.vkpokemonapp.list_screen.presentation

sealed class ListScreenWish {
    data object RequestForNewPokemons : ListScreenWish()
    data class SetPokemonAsOwned(val id: Int, val isOwned: Boolean): ListScreenWish()
}