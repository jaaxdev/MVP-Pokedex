package com.jaax.retrofitmvp.data.model

import com.google.gson.annotations.SerializedName

data class ResultResponse(@SerializedName("results") val listPokemon: List<Result> )