package com.jaax.retrofitmvp.data

import com.jaax.retrofitmvp.data.model.PokemonSingle
import com.jaax.retrofitmvp.data.network.PokemonService
import com.jaax.retrofitmvp.ui.PokemonDialog

class PokemonPresenter(private val view: PokemonDialog, service: PokemonService) : PokemonMVP.Presenter {
    private var model: PokemonModel? = null

    init {
        model = PokemonModel(this, service)
    }

    override fun initGetPokemon(name: String) {
        model?.getPokemon(name)
    }

    override fun setPokemonSingle(pokemon: PokemonSingle) {
        view.updateUI(pokemon)
    }

    override fun showNotSuccess(message: String) {
        view.showNotSuccess(message)
    }

    override fun showError(message: String) {
        view.showError(message)
    }
}