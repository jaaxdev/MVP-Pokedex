package com.jaax.retrofitmvp.data

import com.jaax.retrofitmvp.utils.MainConstants
import com.jaax.retrofitmvp.data.model.PokemonResponse
import com.jaax.retrofitmvp.data.network.PokemonService
import com.jaax.retrofitmvp.data.network.RetrofitClient
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainModel(presenter: MainPresenter): MainMVP.Model {
    private var presenter: MainMVP.Presenter
    init {
        this.presenter = presenter
    }
    override fun getListPokemon(onFinishedListener: MainMVP.Model.OnFinishedListener) {
        val service = RetrofitClient( MainConstants.POKEAPI_BASE_URL ).getService( PokemonService::class.java )
        val call = service.getAllPokemon(MainConstants.LIMIT, presenter.getMyOffset())

        call.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                presenter.setMyLoadable(true)
                if (response.isSuccessful) {
                    val listPokemon = response.body()!!.listPokemon
                    onFinishedListener.onFinished(listPokemon)
                } else {
                    Logger.w("UNSUCCESSFUL")
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                presenter.setMyLoadable(true)
                onFinishedListener.onFailure(t)
            }
        })
    }
}