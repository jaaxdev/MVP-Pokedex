package com.jaax.retrofitmvp.data.network

import com.jaax.retrofitmvp.utils.MainConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun createService(): PokemonService {
        return Retrofit
            .Builder()
            .baseUrl( MainConstants.POKEAPI_BASE_URL )
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create(PokemonService::class.java)
    }
}