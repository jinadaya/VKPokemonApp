package com.example.royaal.vkpokemonapp.list_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.royaal.vkpokemonapp.list_screen.domain.PokemonMainRepository
import com.example.royaal.vkpokemonapp.utils.LoadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListScreenViewModel @Inject constructor(
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