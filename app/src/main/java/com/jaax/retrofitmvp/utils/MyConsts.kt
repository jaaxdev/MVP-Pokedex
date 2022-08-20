package com.jaax.retrofitmvp.utils

class MyConsts {
    companion object {
        const val POKEAPI_BASE_URL = "https://www.pokeapi.co/api/v2/"
        const val POKEMON_ENDPOINT = "pokemon"
        const val POKEMON_IMAGE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
        const val LIMIT = 15
        const val TAG_LOG = "pokedex"

        fun number(number: Int) = "No.° $number"
        fun name(name: String) = "Nombre: ${name.uppercase()}"
        fun heightToMetters(height: Int) = "Altura: ${height/10f}m"
        fun weightToKilograms(weight: Int) = "Peso: ${weight/10f}Kg"
    }
}