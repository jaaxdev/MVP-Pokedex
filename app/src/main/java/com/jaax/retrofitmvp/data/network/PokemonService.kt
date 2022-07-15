package com.jaax.retrofitmvp.data.network

import com.jaax.retrofitmvp.data.model.PokemonResponse
import com.jaax.retrofitmvp.utils.MainConstants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET(MainConstants.POKEMON_ENDPOINT)
    fun getAllPokemon(
        @Query( value = "limit")
        limit: Int,
        @Query( value = "offset" )
        offset: Int
    ): Call<PokemonResponse>
}