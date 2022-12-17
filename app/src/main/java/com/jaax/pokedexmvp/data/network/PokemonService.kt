package com.jaax.pokedexmvp.data.network

import com.jaax.pokedexmvp.data.model.ResultResponse
import com.jaax.pokedexmvp.data.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    fun getAllPokemon(
        @Query( value = "offset" )
        offset: Int,
        @Query( value = "limit")
        limit: Int
    ): Call<ResultResponse>

    @GET("pokemon/{name}")
    fun getPokemonInfo(@Path("name") name: String): Call<Pokemon>
}