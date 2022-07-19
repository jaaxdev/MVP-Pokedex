package com.jaax.retrofitmvp.data

import android.util.Log
import com.jaax.retrofitmvp.utils.MainConstants
import com.jaax.retrofitmvp.data.model.PokemonResponse
import com.jaax.retrofitmvp.data.network.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainModel @Inject constructor(presenter: MainPresenter): MainMVP.Model {
    private var presenter: MainMVP.Presenter
    init {
        this.presenter = presenter
    }
    override suspend fun getListPokemon(onFinishedListener: MainMVP.Model.OnFinishedListener) {
        val service = RetrofitHelper.createService()
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
                    Log.i( MainConstants.TAG_LOG,"UNSUCCESSFUL")
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                presenter.setMyLoadable(true)
                onFinishedListener.onFailure(t)
            }
        })
    }
}