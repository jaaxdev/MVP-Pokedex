package com.jaax.retrofitmvp.di

import android.app.Activity
import com.jaax.retrofitmvp.data.MainMVP
import com.jaax.retrofitmvp.data.MainPresenter
import com.jaax.retrofitmvp.data.network.PokemonService
import com.jaax.retrofitmvp.ui.MainActivity
import com.jaax.retrofitmvp.utils.MyConsts
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
abstract class MainMVPModule {
    @Binds
    abstract fun bindView(view: MainActivity): MainMVP.View

    @Binds
    abstract fun bindPresenter(presenter: MainPresenter): MainMVP.Presenter
}

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @Provides
    fun provideMainActivity(activity: Activity): MainActivity {
        return activity as MainActivity
    }

    @Provides
    fun provideRetrofit(): PokemonService {
        return Retrofit
            .Builder()
            .baseUrl(MyConsts.POKEAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonService::class.java)
    }
}