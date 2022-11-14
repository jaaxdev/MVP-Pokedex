package com.jaax.retrofitmvp.data

import com.jaax.retrofitmvp.data.model.Result

sealed interface MainMVP {

    interface Model {
        suspend fun getListPokemon(onFinishedListener: OnFinishedListener)

        interface OnFinishedListener {
            fun onFinished(listPokemon: List<Result>)
            fun onFailure(t: Throwable)
        }
    }

    interface Presenter {
        suspend fun getMorePokemon()
        fun setIsLoading(isLoading: Boolean)
        fun getIsLoading(): Boolean
        fun increaseOffset()
        fun getOffset(): Int
        fun itemsLimit(): Int
        fun cancelProgressbar()
        fun notifyUnsuccess()
    }

    interface View {
        fun showPokemon(listPokemon: List<Result>)
        fun cancelProgressbar()
        fun showErrorMessage()
        fun showUnsuccessMessage()
    }
}