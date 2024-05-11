package com.example.royaal.foundation.utils

sealed interface LoadingState {
    data object Loading : LoadingState
    data object Completed : LoadingState
    data class LoadError(val error: Throwable? = null) : LoadingState
}