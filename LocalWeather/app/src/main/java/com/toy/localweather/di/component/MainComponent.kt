package com.toy.localweather.di.component

import com.toy.localweather.di.component.MainComponent
import com.toy.localweather.ui.activity.MainActivity
import com.toy.localweather.di.scopes.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface MainComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create() : MainComponent
    }
    fun inject(mainActivity: MainActivity)
}