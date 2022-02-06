package com.example.pokemoncleanarchitectureapplication.domain.repository

import com.example.pokemoncleanarchitectureapplication.data.remote.dto.PokemonDto
import com.example.pokemoncleanarchitectureapplication.data.remote.dto.PokemonListDto

interface PokemonRepository {

    suspend fun getPokemonList(offset: Int, limit: Int): PokemonListDto
    suspend fun getPokemonInfo(name: String): PokemonDto


}