package com.jaax.retrofitmvp.data

import android.util.Log
import com.jaax.retrofitmvp.utils.MyConsts
import com.jaax.retrofitmvp.data.model.ResultResponse
import com.jaax.retrofitmvp.data.network.PokemonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainModel @Inject constructor(
    private val presenter: MainPresenter,
    private val service: PokemonService) : MainMVP.Model {

    override suspend fun getListPokemon(onFinishedListener: MainMVP.Model.OnFinishedListener) {
        val call = service.getAllPokemon(MyConsts.LIMIT, presenter.getMyOffset())

        call.enqueue(object : Callback<ResultResponse> {
            override fun onResponse(
                call: Call<ResultResponse>,
                response: Response<ResultResponse>
            ) {
                presenter.setMyLoadable(true)
                if (response.isSuccessful) {
                    val listPokemon = response.body()!!.listPokemon
                    presenter.cancelProgressbar()
                    onFinishedListener.onFinished(listPokemon)
                } else {
                    Log.i(MyConsts.TAG_LOG, "UNSUCCESSFUL")
                }
            }

            override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                presenter.setMyLoadable(true)
                onFinishedListener.onFailure(t)
            }
        })
    }
}