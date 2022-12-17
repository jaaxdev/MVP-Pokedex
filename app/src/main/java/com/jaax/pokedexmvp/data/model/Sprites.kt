package com.jaax.pokedexmvp.data.model

import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_default_female")
    val frontFemale: String?,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: String?
)