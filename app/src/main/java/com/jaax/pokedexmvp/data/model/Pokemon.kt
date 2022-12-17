package com.jaax.pokedexmvp.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id")
    val number: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val stats: List<Stats>,
    val types: List<Types>
)