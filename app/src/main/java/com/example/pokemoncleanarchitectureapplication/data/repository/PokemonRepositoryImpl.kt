package com.example.pokemoncleanarchitectureapplication.data.repository

import com.example.pokemoncleanarchitectureapplication.data.remote.PokemonApi
import com.example.pokemoncleanarchitectureapplication.data.remote.dto.PokemonDto
import com.example.pokemoncleanarchitectureapplication.data.remote.dto.PokemonListDto
import com.example.pokemoncleanarchitectureapplication.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi
) : PokemonRepository {

    override suspend fun getPokemonList( offset: Int,limit: Int): PokemonListDto {
        return pokemonApi.getPokemonList(
            offset = offset, limit = limit,
        )
    }

    override suspend fun getPokemonInfo(name: String): PokemonDto {
        return pokemonApi.getPokemonInfo(name = name)
    }
}