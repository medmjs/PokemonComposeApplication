package com.example.pokemoncleanarchitectureapplication.utile

sealed class Screen(val route: String) {

    object PokemonListScreen : Screen("pokemon_list_screen")
    object PokemonInfoScreen : Screen("pokemon_info_screen")
}
