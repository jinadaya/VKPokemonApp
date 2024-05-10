package com.example.royaal.vkpokemonapp.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.royaal.vkpokemonapp.details.domain.PokemonDetailsRepository
import com.example.royaal.vkpokemonapp.di.qualifiers.PokemonIdQualifier
import com.example.royaal.vkpokemonapp.utils.LoadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
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