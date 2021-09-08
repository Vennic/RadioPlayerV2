package com.kuzheevadel.radioplayerv2.di

import com.kuzheevadel.core.di.CoreComponent
import com.kuzheevadel.core.di.scopes.AppScope
import com.kuzheevadel.radioplayerv2.services.PlayerService
import dagger.Component

@AppScope
@Component(dependencies = [CoreComponent::class])
interface AppComponent {

    fun inject(service: PlayerService)
}