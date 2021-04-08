package com.toy.localweather

import android.app.Application
import com.toy.localweather.di.component.ApplicationComponent
import com.toy.localweather.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class LocalWeatherApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector


}