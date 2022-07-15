package com.jaax.retrofitmvp.data

import com.jaax.retrofitmvp.data.model.Pokemon

interface MainMVP {

    interface Model {
        fun getListPokemon(onFinishedListener: OnFinishedListener)

        interface OnFinishedListener {
            fun onFinished(listPokemon: List<Pokemon>)
            fun onFailure(t: Throwable)
        }
    }

    interface Presenter {
        fun firstRequest()
        fun getMorePokemon()
        fun setMyLoadable(canLoad: Boolean)
        fun getMyLoadable(): Boolean
        fun increaseOffset(increment: Int)
        fun getMyOffset(): Int
    }

    interface View {
        fun showPokemon(listPokemon: List<Pokemon>)
    }
}