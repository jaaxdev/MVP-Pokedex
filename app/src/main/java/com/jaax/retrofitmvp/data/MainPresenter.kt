package com.jaax.retrofitmvp.data

import android.util.Log
import com.jaax.retrofitmvp.data.model.Result
import com.jaax.retrofitmvp.data.network.PokemonService
import com.jaax.retrofitmvp.ui.MainActivity
import javax.inject.Inject

class MainPresenter @Inject constructor(private val view: MainActivity, service: PokemonService) :
    MainMVP.Presenter,
    MainMVP.Model.OnFinishedListener {

    private var model: MainMVP.Model? = null
    private var offset = 0
    private var limit = 20
    private var isLoading = false

    init {
        model = MainModel(this, service)
    }

    override suspend fun getMorePokemon() {
        model!!.getListPokemon(this)
    }

    override fun setIsLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    override fun getIsLoading(): Boolean {
        return this.isLoading
    }

    override fun increaseOffset() {
        this.offset += limit
    }

    override fun getOffset(): Int {
        return this.offset
    }

    override fun itemsLimit(): Int {
        return this.limit
    }

    override fun cancelProgressbar() {
        view.cancelProgressbar()
    }

    override fun notifyUnsuccess() {
        view
    }

    override fun onFinished(listPokemon: List<Result>) {
        view.showPokemon(listPokemon)
    }

    override fun onFailure(t: Throwable) {
        Log.e("jaaax", t.message.toString())
        view.showErrorMessage()
    }
}