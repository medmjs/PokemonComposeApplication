package com.example.pokemoncleanarchitectureapplication.di.module

import com.example.pokemoncleanarchitectureapplication.data.remote.PokemonApi
import com.example.pokemoncleanarchitectureapplication.data.repository.PokemonRepositoryImpl
import com.example.pokemoncleanarchitectureapplication.domain.repository.PokemonRepository
import com.example.pokemoncleanarchitectureapplication.utile.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun proviedePokemonApi(): PokemonApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokemonApi::class.java)

    @Provides
    @Singleton
    fun providePokemonRepository(pokemonApi: PokemonApi):PokemonRepository{
        return PokemonRepositoryImpl(pokemonApi = pokemonApi )
    }


}