package com.kuzheevadel.radioplayerv2.usecases

import com.kuzheevadel.radioplayerv2.audio.di.AudioFragmentScope
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import javax.inject.Inject

@AudioFragmentScope
class SaveAudioDataUseCaseImpl @Inject constructor(
    val audioRepo: AudioRepository
): SaveAudioDataUseCase {

    override suspend fun addAudioInPlaylist(audioPos: Int, playlistPos: Int) {
        audioRepo.addAudioInPlaylist(
            audioRepo.getAllAudio()[audioPos], audioRepo.getAllPlaylists()[playlistPos])
    }

    override suspend fun addAudioListInPlaylist(audioIdList: List<Long>, playlistPos: Int) {
        audioRepo.addAudioListInPlaylist(audioIdList, playlistPos)
    }
}

interface SaveAudioDataUseCase {
    suspend fun addAudioInPlaylist(audioPos: Int, playlistPos: Int)
    suspend fun addAudioListInPlaylist(audioIdList: List<Long>, playlistPos: Int)
}