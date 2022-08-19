package com.jaax.retrofitmvp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonSingle(
    @SerializedName("id")
    val number: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<Type>
)