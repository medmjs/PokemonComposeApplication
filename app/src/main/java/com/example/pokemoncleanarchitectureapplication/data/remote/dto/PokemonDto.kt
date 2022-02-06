package com.example.pokemoncleanarchitectureapplication.data.remote.dto

import com.example.pokemoncleanarchitectureapplication.domain.model.pokemonInfo.Pokemon

data class PokemonDto(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val game_indices: List<GameIndice>,
    val height: Int,
    val held_items: List<HeldItem>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val past_types: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)

fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        abilities = abilities,
        id = id,
        moves = moves,
        name = name,
        order = order,
        species = species,
        sprites = sprites,
        stats = stats,
        types = types,
        weight = weight,
        height = height
    )
}