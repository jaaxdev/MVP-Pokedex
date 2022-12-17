package com.jaax.pokedexmvp.data.model

import com.google.gson.annotations.SerializedName

data class Result(
    val name: String,
    @SerializedName("url")
    val numberURL: String ) {

    fun getNumber(): Int {
        val arrayNumberImg = numberURL.split( "/" )
        return arrayNumberImg[arrayNumberImg.size - 2].toInt()
    }
}