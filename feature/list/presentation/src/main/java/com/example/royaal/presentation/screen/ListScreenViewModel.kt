package com.example.royaal.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.royaal.domain.PokemonMainRepository
import com.example.royaal.foundation.utils.LoadingState
import com.example.royaal.presentation.models.toUiPokemonList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class ListScreenViewModel @Inject constructor(
    private val pokemonMainRepository: PokemonMainRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ListScreenState())
    val state: StateFlow<ListScreenState> = _state
    val value get() = state.value

    init {
        viewModelScope.launch {
            pokemonMainRepository.requestMorePokemons()
        }
        viewModelScope.launch {
            pokemonMainRepository
                .getPokemonList()
                .collect { newPokemons ->
                    _state.update {
                        it.copy(
                            loadingState = LoadingState.Completed,
                            pokemons = newPokemons.toUiPokemonList()
                        )
                    }
                }
        }
    }

    fun consume(wish: ListScreenWish) {
        viewModelScope.launch {
            when (wish) {
                ListScreenWish.RequestForNewPokemons -> pokemonMainRepository.requestMorePokemons()

                is ListScreenWish.SetPokemonAsOwned -> pokemonMainRepository.changePokemonStatus(
                    id = wish.id,
                    isOwned = wish.isOwned
                )
            }
        }
    }
}