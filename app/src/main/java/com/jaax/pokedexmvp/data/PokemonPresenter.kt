package com.jaax.pokedexmvp.data

import com.jaax.pokedexmvp.data.model.Pokemon
import com.jaax.pokedexmvp.data.network.PokemonService
import com.jaax.pokedexmvp.ui.PokemonDialog
import javax.inject.Inject

class PokemonPresenter @Inject constructor(
    private val view: PokemonDialog,
    service: PokemonService
) : PokemonMVP.Presenter {

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