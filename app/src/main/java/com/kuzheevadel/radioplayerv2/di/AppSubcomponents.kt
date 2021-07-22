package com.kuzheevadel.radioplayerv2.di

import com.kuzheevadel.radioplayerv2.radio.di.RadioComponent
import com.kuzheevadel.radioplayerv2.audio.di.TracksComponent
import dagger.Module

@Module(subcomponents = [TracksComponent::class, RadioComponent::class])
class AppSubcomponents