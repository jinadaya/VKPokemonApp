package com.example.royaal.presentation.screen

internal sealed class DetailScreenWish {
    data class SetPokemonNewStatus(val newStatus: Boolean) : DetailScreenWish()
}