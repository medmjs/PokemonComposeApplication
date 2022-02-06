package com.example.pokemoncleanarchitectureapplication.data.remote.dto

import android.util.Log
import com.example.pokemoncleanarchitectureapplication.domain.model.pokemonList.PokemonList

data class PokemonListDto(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)


fun PokemonListDto.toPokemonList(): PokemonList {
    return PokemonList(
        count = count,
        next = next,
        previous = previous,
        results = results.map {
            it.url = it.url.dropLast(1)
            val id = it.url.takeLastWhile { char ->
                char.isDigit()
            }
            it.url =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

            it
        }
    )
}