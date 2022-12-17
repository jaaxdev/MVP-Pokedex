package com.jaax.pokedexmvp.data

import com.jaax.pokedexmvp.data.model.Pokemon

interface PokemonMVP {

    interface Model {
        suspend fun getPokemon(name: String)
    }

    interface Presenter {
        suspend fun getPokemon(name: String)
        fun setPokemonSingle(pokemon: Pokemon)
        fun showNotSuccess(message: String)
        fun showError(message: String)
        fun stopProgressbar()
    }

    interface View {
        fun updateUI(pokemon: Pokemon)
        fun stopProgressbar()
        fun showNotSuccess(message: String)
        fun showError(message: String)
    }
}