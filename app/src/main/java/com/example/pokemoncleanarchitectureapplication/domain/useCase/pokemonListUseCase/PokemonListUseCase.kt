package com.example.pokemoncleanarchitectureapplication.domain.useCase.pokemonListUseCase

import com.example.pokemoncleanarchitectureapplication.data.remote.dto.toPokemonList
import com.example.pokemoncleanarchitectureapplication.domain.model.pokemonList.PokemonList
import com.example.pokemoncleanarchitectureapplication.domain.repository.PokemonRepository
import com.example.pokemoncleanarchitectureapplication.utile.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class PokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    operator fun invoke(offset: Int, limit: Int): Flow<Response<PokemonList>> = flow {

        try {
            emit(Response.Loading<PokemonList>())

            val result = pokemonRepository.getPokemonList(offset, limit).toPokemonList()

            emit(Response.Success<PokemonList>(result))

        } catch (ex: IOException) {
            emit(Response.Error<PokemonList>(ex.localizedMessage))
        } catch (ex: HttpException) {
            emit(Response.Error<PokemonList>(ex.localizedMessage))
        }

    }


}