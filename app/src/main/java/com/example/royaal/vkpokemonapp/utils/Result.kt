package com.example.royaal.vkpokemonapp.utils

sealed class Result<T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Error<T>(val prevValue: T? = null, val error: Throwable) : Result<T>()
}