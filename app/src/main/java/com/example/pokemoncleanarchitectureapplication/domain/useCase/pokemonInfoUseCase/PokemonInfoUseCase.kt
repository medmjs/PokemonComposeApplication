package com.example.pokemoncleanarchitectureapplication.domain.useCase.pokemonInfoUseCase

import com.example.pokemoncleanarchitectureapplication.data.remote.dto.toPokemon
import com.example.pokemoncleanarchitectureapplication.domain.model.pokemonInfo.Pokemon
import com.example.pokemoncleanarchitectureapplication.domain.repository.PokemonRepository
import com.example.pokemoncleanarchitectureapplication.utile.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PokemonInfoUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    operator fun invoke(name: String): Flow<Response<Pokemon>> = flow {

        try {
            emit(Response.Loading<Pokemon>())
            val result = pokemonRepository.getPokemonInfo(name = name).toPokemon()
            emit(Response.Success<Pokemon>(result))
        } catch (ex: IOException) {
            emit(Response.Error<Pokemon>(message = ex.localizedMessage))
        } catch (ex: HttpException) {
            emit(
                Response.Error<Pokemon>(message = ex.localizedMessage)
            )
        }

    }

}