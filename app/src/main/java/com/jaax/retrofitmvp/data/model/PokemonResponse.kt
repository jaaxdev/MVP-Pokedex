package com.jaax.retrofitmvp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse( @SerializedName("results") val listPokemon: List<Pokemon> )