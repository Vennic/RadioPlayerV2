package com.kuzheevadel.radioplayerv2.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppSubcomponents::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): AppComponent
    }
}