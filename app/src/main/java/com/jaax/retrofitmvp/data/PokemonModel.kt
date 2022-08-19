package com.jaax.retrofitmvp.data

import com.jaax.retrofitmvp.data.model.PokemonSingle
import com.jaax.retrofitmvp.data.network.PokemonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonModel(
    private val presenter: PokemonPresenter,
    private val service: PokemonService) : PokemonMVP.Model {

    override fun getPokemon(name: String) {
        val call = service.getPokemonInfo(name)

        call.enqueue(object : Callback<PokemonSingle> {
            override fun onResponse(call: Call<PokemonSingle>, response: Response<PokemonSingle>) {
                if(response.isSuccessful) {
                    presenter.setPokemonSingle(response.body()!!)
                } else {
                    presenter.showNotSuccess(response.message())
                }
            }

            override fun onFailure(call: Call<PokemonSingle>, t: Throwable) {
                presenter.showError(t.message!!)
            }
        })
    }
}