package com.jaax.retrofitmvp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient( urlBase: String ) {
    private val retrofitClient = Retrofit
        .Builder()
        .baseUrl( urlBase )
        .addConverterFactory( GsonConverterFactory.create() )
        .build()

    fun <T> getService( service: Class<T> ): T {
        return retrofitClient.create( service )
    }
}