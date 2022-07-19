package com.jaax.retrofitmvp.data

import com.jaax.retrofitmvp.data.model.Pokemon
import com.jaax.retrofitmvp.ui.MainActivity
import com.orhanobut.logger.Logger
import javax.inject.Inject

class MainPresenter @Inject constructor(view: MainActivity): MainMVP.Presenter,
    MainMVP.Model.OnFinishedListener {

    private var view: MainMVP.View? = null
    private var model: MainMVP.Model? = null
    private var offset = 0
    private var loadable = false

    init {
        this.view = view
        model = MainModel(this)
    }

    override suspend fun getMorePokemon() {
        model!!.getListPokemon(this)
    }

    override fun setMyLoadable(canLoad: Boolean) {
        this.loadable = canLoad
    }

    override fun getMyLoadable(): Boolean {
        return this.loadable
    }

    override fun increaseOffset(increment: Int) {
        this.offset += increment
    }

    override fun getMyOffset(): Int {
        return this.offset
    }

    override fun onFinished(listPokemon: List<Pokemon>) {
        view!!.showPokemon(listPokemon)
    }

    override fun onFailure(t: Throwable) {
        Logger.e(t.message.toString())
    }
}