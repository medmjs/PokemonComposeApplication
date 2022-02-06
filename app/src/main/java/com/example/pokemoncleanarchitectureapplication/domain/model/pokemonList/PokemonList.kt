package com.example.pokemoncleanarchitectureapplication.domain.model.pokemonList

import com.example.pokemoncleanarchitectureapplication.data.remote.dto.*

data class PokemonList(
    val count: Int = 0,
    val next: String = "",
    val previous: Any? = null,
    var results: List<Result> = emptyList()
)