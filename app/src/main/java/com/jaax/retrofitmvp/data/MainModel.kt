package com.jaax.retrofitmvp.data

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
        val call = service.getAllPokemon(presenter.getOffset(), presenter.itemsLimit())

        call.enqueue(object : Callback<ResultResponse> {
            override fun onResponse(call: Call<ResultResponse>, response: Response<ResultResponse>) {
                if (response.isSuccessful) {
                    val results = response.body()!!
                    onFinishedListener.onFinished(results.listPokemon)
                    presenter.cancelProgressbar()
                } else {
                    presenter.notifyUnsuccess()
                }
            }

            override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })
    }
}