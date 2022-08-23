package com.jaax.retrofitmvp.data

import com.jaax.retrofitmvp.data.model.Pokemon

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