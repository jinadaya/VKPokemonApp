package com.example.royaal.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.royaal.domain.PokemonDetailsRepository
import com.example.royaal.foundation.utils.LoadingState
import com.example.royaal.presentation.di.PokemonIdQualifier
import com.example.royaal.presentation.model.toUiStats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class DetailsViewModel @Inject constructor(
    @PokemonIdQualifier
    id: Int,
    private val repo: PokemonDetailsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailScreenState(id = id))
    val state: StateFlow<DetailScreenState> = _state
    private val value get() = state.value

    init {
        viewModelScope.launch {
            repo.getPokemonInfo(id).collect { newPokemon ->
                _state.update {
                    it.copy(
                        loadingState = LoadingState.Completed,
                        name = newPokemon.name,
                        shinyPoster = newPokemon.shinyPoster,
                        defaultPoster = newPokemon.defaultPoster,
                        isOwned = newPokemon.isOwned,
                        stats = newPokemon.stats.toUiStats()
                    )
                }
            }
        }
    }

    fun consume(wish: DetailScreenWish) {
        viewModelScope.launch {
            when (wish) {
                is DetailScreenWish.SetPokemonNewStatus -> repo.changePokemonStatus(
                    value.id,
                    wish.newStatus
                )
            }
        }
    }
}