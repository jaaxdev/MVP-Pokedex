package com.jaax.retrofitmvp.data.network

import com.jaax.retrofitmvp.data.model.ResultResponse
import com.jaax.retrofitmvp.data.model.Pokemon
import com.jaax.retrofitmvp.utils.MyConsts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET(MyConsts.POKEMON_ENDPOINT)
    fun getAllPokemon(
        @Query( value = "limit")
        limit: Int,
        @Query( value = "offset" )
        offset: Int
    ): Call<ResultResponse>

    @GET("${MyConsts.POKEMON_ENDPOINT}/{name}")
    fun getPokemonInfo(@Path("name") name: String): Call<Pokemon>
}