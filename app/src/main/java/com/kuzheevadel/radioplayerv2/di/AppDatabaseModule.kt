package com.kuzheevadel.radioplayerv2.di

import android.content.Context
import androidx.room.Room
import com.kuzheevadel.radioplayerv2.database.AppDatabase
import com.kuzheevadel.radioplayerv2.database.PlaylistInfoConverter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "AppDatabase")
            .build()
    }
}