package com.jaax.retrofitmvp.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val numberURL: String ) {

    fun getNumber(): Int {
        val arrayNumberImg = numberURL.split( "/" )
        return arrayNumberImg[arrayNumberImg.size - 2].toInt()
    }
}