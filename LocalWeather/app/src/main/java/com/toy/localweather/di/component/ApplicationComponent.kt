package com.toy.localweather.di.component

import android.content.Context
import com.toy.localweather.di.component.ApplicationComponent
import com.toy.localweather.di.component.MainComponent
import com.toy.localweather.di.builder.ActivityBuilder
import com.toy.localweather.di.module.NetworkModule
import com.toy.localweather.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityBuilder::class, ViewModelModule::class,NetworkModule::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }

    fun mainComponent() : MainComponent.Factory
}