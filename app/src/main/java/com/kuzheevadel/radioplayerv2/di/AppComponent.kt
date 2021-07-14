package com.kuzheevadel.radioplayerv2.di

import android.content.Context
import com.kuzheevadel.radioplayerv2.alltracks.di.AllTracksComponent
import dagger.BindsInstance
import dagger.Component

@Component(modules = [
    ViewModelBuilderModule::class,
    AppSubcomponents::class,
    RepoModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): AppComponent
    }

    fun getAllTracksComponent(): AllTracksComponent.Factory
}