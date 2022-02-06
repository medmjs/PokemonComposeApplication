package com.example.pokemoncleanarchitectureapplication.data.remote

import com.example.pokemoncleanarchitectureapplication.data.remote.dto.PokemonDto
import com.example.pokemoncleanarchitectureapplication.data.remote.dto.PokemonListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {


    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): PokemonListDto


    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ): PokemonDto


}