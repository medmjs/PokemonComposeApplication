package com.example.pokemoncleanarchitectureapplication.domain.model.pokemonInfo

data class PokemonState(
    val loading: Boolean = false,
    val pokemonInfo: Pokemon? = null,
    val error: String? = null
)
