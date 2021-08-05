package com.kuzheevadel.radioplayerv2.di

import com.kuzheevadel.radioplayerv2.radio.di.RadioComponent
import com.kuzheevadel.radioplayerv2.audio.di.AudioComponent
import dagger.Module

@Module(subcomponents = [AudioComponent::class, RadioComponent::class])
class AppSubcomponents