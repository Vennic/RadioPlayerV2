package com.kuzheevadel.core.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.kuzheevadel.core.database.PlaylistAudioDao
import com.kuzheevadel.core.di.modules.*
import com.kuzheevadel.core.repositories.AudioRepository
import com.kuzheevadel.core.repositories.PlayerMediaRepository
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoreModule::class,
        CoreRepoModule::class,
        DispatchersModule::class,
        CoreDatabaseModule::class
    ]
)
interface CoreComponent {

    fun context(): Context
    fun audioRepo(): AudioRepository
    fun playlistDao(): PlaylistAudioDao
    fun playerRepo(): PlayerMediaRepository
    fun defDispatcher(): CoroutineDispatcher
}