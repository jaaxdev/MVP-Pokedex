package com.jaax.pokedexmvp.di

import android.app.Activity
import com.jaax.pokedexmvp.data.*
import com.jaax.pokedexmvp.data.network.PokemonService
import com.jaax.pokedexmvp.ui.MainActivity
import com.jaax.pokedexmvp.ui.PokemonDialog
import com.jaax.pokedexmvp.utils.MyConsts
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
abstract class MVPModule {
    @Binds
    abstract fun bindMainView(view: MainActivity): MainMVP.View

    @Binds
    abstract fun bindMainPresenter(presenter: MainPresenter): MainMVP.Presenter

    @Binds
    abstract fun bindPokemonView(view: PokemonDialog): PokemonMVP.View

    @Binds
    abstract fun bindPokemonPresenter(presenter: PokemonPresenter): PokemonMVP.Presenter
}

@Module
@InstallIn(ActivityComponent::class)
object MainModule {
    @Provides
    fun provideMainActivity(activity: Activity) = activity as MainActivity
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(MyConsts.POKEAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePokemonService(retrofit: Retrofit) = retrofit.create(PokemonService::class.java)
}