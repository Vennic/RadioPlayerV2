package com.kuzheevadel.radioplayerv2.services

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.kuzheevadel.core.common.MediaType
import com.kuzheevadel.core.models.Audio
import com.kuzheevadel.core.models.RadioStation
import com.kuzheevadel.core.repositories.PlayerMediaRepository
import com.kuzheevadel.radioplayerv2.di.PlayerApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayerService : Service() {

    @Inject
    lateinit var playerRepo: PlayerMediaRepository

    private lateinit var exoPlayer: SimpleExoPlayer
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    private var currentAudio: Audio? = null
    private var currentRadio: RadioStation? = null

    override fun onCreate() {
        super.onCreate()
        (application as PlayerApplication)
            .getAppComponent()
            .inject(this)

        initExoPlayer()
        subscribeService()
    }


    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun initExoPlayer() {
        exoPlayer = SimpleExoPlayer.Builder(applicationContext).build()
    }

    private fun prepareAudio(uri: Uri) {
        val mediaItem = MediaItem.fromUri(uri)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
    }

    private fun playOrPausePlayer(isPlaying: Boolean) {
        if (isPlaying) {
            exoPlayer.play()
        } else {
            exoPlayer.pause()
        }
    }

    private fun subscribeService() {
        serviceScope.launch {
            playerRepo.getStateCurrentMediaData().collect { mediaType ->
                when(mediaType) {
                    is MediaType.Audio -> {
                        if (currentAudio == null || currentAudio?.id != mediaType.audio.id) {
                            currentAudio = mediaType.audio
                            currentRadio = null

                            prepareAudio(mediaType.audio.uri)
                            playOrPausePlayer(mediaType.audio.isPlaying)
                        } else if (mediaType.audio.id == currentAudio?.id) {
                            playOrPausePlayer(mediaType.audio.isPlaying)
                        }

                    }
                    is MediaType.RadioStation -> {
                        currentAudio = null

                    }
                    else -> {
                        currentAudio = null
                        currentRadio = null
                    }
                }
            }
        }
    }

}