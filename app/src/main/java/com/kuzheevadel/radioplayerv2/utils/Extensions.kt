package com.kuzheevadel.radioplayerv2.utils

import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.models.AudioEntity

fun List<Audio>.setAudioState(audio: Audio): List<Audio> {
    for (item in this) {
        if (item.id == audio.id) {
            item.apply {
                isPlaying = audio.isPlaying
                isSelected = audio.isSelected
            }
        } else {
            item.isSelected = false
            item.isPlaying = false
        }
    }
    return this
}

fun List<Audio>.setAllAudioUnselected(): List<Audio> {
    for (item in this) {
        item.apply {
            isPlaying = false
            isSelected = false
        }
    }
    return this
}

fun List<Audio>.mapToAudioEntity(): List<AudioEntity> {
    return this.map { audio ->
        with(audio) {
            AudioEntity(name,id,title,artistName,albumTitle,duration,albumId)
        }
    }
}