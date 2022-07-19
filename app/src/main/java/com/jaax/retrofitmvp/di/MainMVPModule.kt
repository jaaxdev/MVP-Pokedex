package com.jaax.retrofitmvp.di

import android.app.Activity
import com.jaax.retrofitmvp.data.MainMVP
import com.jaax.retrofitmvp.data.MainModel
import com.jaax.retrofitmvp.data.MainPresenter
import com.jaax.retrofitmvp.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

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
}