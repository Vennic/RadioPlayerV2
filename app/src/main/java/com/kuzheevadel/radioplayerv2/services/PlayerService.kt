package com.kuzheevadel.radioplayerv2.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.android.exoplayer2.SimpleExoPlayer
import com.kuzheevadel.radioplayerv2.repositories.PlayerMediaRepository
import javax.inject.Inject


class PlayerService : Service() {

    @Inject
    lateinit var playerRepo: PlayerMediaRepository

    private lateinit var exoPlayer: SimpleExoPlayer

    override fun onCreate() {
        super.onCreate()

    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}