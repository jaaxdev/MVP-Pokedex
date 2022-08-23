package com.jaax.retrofitmvp.data

import com.jaax.retrofitmvp.data.model.Pokemon
import com.jaax.retrofitmvp.data.network.PokemonService
import com.jaax.retrofitmvp.ui.PokemonDialog

class PokemonPresenter(private val view: PokemonDialog, service: PokemonService) : PokemonMVP.Presenter {
    private var model: PokemonModel? = null

    init {
        model = PokemonModel(this, service)
    }

    override suspend fun getPokemon(name: String) {
        model?.getPokemon(name)
    }

    override fun setPokemonSingle(pokemon: Pokemon) {
        view.updateUI(pokemon)
    }

    override fun showNotSuccess(message: String) {
        view.showNotSuccess(message)
    }

    override fun showError(message: String) {
        view.showError(message)
    }

    override fun stopProgressbar() {
        view.stopProgressbar()
    }
}