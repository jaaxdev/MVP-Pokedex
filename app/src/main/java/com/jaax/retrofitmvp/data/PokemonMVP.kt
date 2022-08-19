package com.jaax.retrofitmvp.data

import com.jaax.retrofitmvp.data.model.PokemonSingle

interface PokemonMVP {

    interface Model {
        fun getPokemon(name: String)
    }

    interface Presenter {
        fun initGetPokemon(name: String)
        fun setPokemonSingle(pokemon: PokemonSingle)
        fun showNotSuccess(message: String)
        fun showError(message: String)
    }

    interface View {
        fun updateUI(pokemon: PokemonSingle)
        fun showNotSuccess(message: String)
        fun showError(message: String)
    }
}