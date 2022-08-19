package com.jaax.retrofitmvp.data

import com.jaax.retrofitmvp.data.model.Result

interface MainMVP {

    interface Model {
        suspend fun getListPokemon(onFinishedListener: OnFinishedListener)

        interface OnFinishedListener {
            fun onFinished(listPokemon: List<Result>)
            fun onFailure(t: Throwable)
        }
    }

    interface Presenter {
        suspend fun getMorePokemon()
        fun setMyLoadable(canLoad: Boolean)
        fun getMyLoadable(): Boolean
        fun increaseOffset(increment: Int)
        fun getMyOffset(): Int
    }

    interface View {
        fun showPokemon(listPokemon: List<Result>)
    }
}