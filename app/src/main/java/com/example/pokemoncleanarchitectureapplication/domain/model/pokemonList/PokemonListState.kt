package com.example.pokemoncleanarchitectureapplication.domain.model.pokemonList

data class PokemonListState(
    var loading: Boolean = false,
    var pokemons: List<PokemonEntry> = emptyList(),
    var error: String = "",
)
