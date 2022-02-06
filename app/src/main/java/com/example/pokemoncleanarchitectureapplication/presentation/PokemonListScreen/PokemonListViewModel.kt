package com.example.pokemoncleanarchitectureapplication.presentation.PokemonListScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemoncleanarchitectureapplication.data.remote.dto.Result
import com.example.pokemoncleanarchitectureapplication.domain.model.pokemonInfo.PokemonState
import com.example.pokemoncleanarchitectureapplication.domain.model.pokemonList.PokemonEntry
import com.example.pokemoncleanarchitectureapplication.domain.model.pokemonList.PokemonList
import com.example.pokemoncleanarchitectureapplication.domain.model.pokemonList.PokemonListState
import com.example.pokemoncleanarchitectureapplication.domain.useCase.pokemonListUseCase.PokemonListUseCase
import com.example.pokemoncleanarchitectureapplication.utile.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonListUseCase: PokemonListUseCase
) : ViewModel() {


    private var _state = mutableStateOf<PokemonListState>(PokemonListState())
    val state: State<PokemonListState> = _state

    var list = mutableStateOf<List<PokemonEntry>>(listOf())

    init {
        getPokemonList(1, 20)
    }


    var isSearching = mutableStateOf(false)
    var isStartSearch = true
    var catchList = PokemonListState()


    fun searchQuery(query: String) {

        val listToSearch = if (isStartSearch) {
            _state.value
        } else {
            catchList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {

                _state.value = catchList
                isSearching.value = false
                isStartSearch = true
                return@launch

            }
            val result = listToSearch?.pokemons!!.filter {
                it.pokemonName.contains(
                    query.trim(),
                    ignoreCase = true
                )
            }

            if (isStartSearch) {
                catchList = _state.value
                isStartSearch = false
            }

            _state.value = PokemonListState(pokemons = result)

        }


    }


    fun getPokemonList(offset: Int, limit: Int) {
        Log.d("TAG", "getPokemonList: $offset")
        pokemonListUseCase.invoke(offset, limit).onEach { response ->
            when (response) {
                is Response.Loading -> {
                    _state.value = PokemonListState(loading = true)
                }
                is Response.Success -> {
                    var pokemonList = response.data?.results

                    list.value += pokemonList!!.map {
                        PokemonEntry(pokemonName = it.name, pokemonImage = it.url)
                    }
//                    response.data?.results = list.value
                    _state.value = PokemonListState(pokemons = list.value)
                }
                is Response.Error -> {
                    _state.value = PokemonListState(error = response.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }
}