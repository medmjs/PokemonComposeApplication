package com.example.pokemoncleanarchitectureapplication.domain.model.pokemonInfo

import com.example.pokemoncleanarchitectureapplication.data.remote.dto.*

data class Pokemon(
    val abilities: List<Ability>,
    val id: Int,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int,
    val height:Int
)