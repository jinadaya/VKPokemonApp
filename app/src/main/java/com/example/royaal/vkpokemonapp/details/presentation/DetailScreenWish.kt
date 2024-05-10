package com.example.royaal.vkpokemonapp.details.presentation

sealed class DetailScreenWish {
    data class SetPokemonNewStatus(val newStatus: Boolean) : DetailScreenWish()
}