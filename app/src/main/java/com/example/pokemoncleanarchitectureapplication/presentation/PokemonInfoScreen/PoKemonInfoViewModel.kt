package com.example.pokemoncleanarchitectureapplication.presentation.PokemonInfoScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemoncleanarchitectureapplication.domain.model.pokemonInfo.Pokemon
import com.example.pokemoncleanarchitectureapplication.domain.model.pokemonInfo.PokemonState
import com.example.pokemoncleanarchitectureapplication.domain.useCase.pokemonInfoUseCase.PokemonInfoUseCase
import com.example.pokemoncleanarchitectureapplication.utile.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PoKemonInfoViewModel @Inject constructor(
    private val getPokemonInfoUseCase: PokemonInfoUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _state = mutableStateOf<PokemonState>(PokemonState())

    val state: State<PokemonState> = _state

    init {

        savedStateHandle.get<String>("pokemonName").let {
            if (it != null) {
                getPokemonInfo(it)
            }
        }

    }


    fun getPokemonInfo(name: String) {

        getPokemonInfoUseCase(name = name).onEach {

            when (it) {

                is Response.Loading<Pokemon> -> {
                    _state.value = PokemonState(loading = true)
                }
                is Response.Success<Pokemon> -> {
                    _state.value = PokemonState(pokemonInfo = it.data)
                }
                is Response.Error<Pokemon> -> {
                    _state.value = PokemonState(error = it.message)
                }


            }


        }.launchIn(viewModelScope)


    }


}