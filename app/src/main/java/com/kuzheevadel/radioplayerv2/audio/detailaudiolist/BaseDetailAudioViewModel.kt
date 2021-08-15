package com.kuzheevadel.radioplayerv2.audio.detailaudiolist

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.common.DetailAudioItem
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.models.Playlist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseDetailAudioViewModel: ViewModel() {

    lateinit var audioFlow: Flow<List<Audio>>

    lateinit var updateListFlow: MutableStateFlow<Boolean>

    lateinit var playlistFlow: Flow<List<Playlist>>

    abstract fun getDetailItem(): DetailAudioItem

    abstract fun init(position: Int)

    abstract fun onAudioClicked(position: Int)

    abstract fun getAudioList(): List<Audio>

    abstract fun updateList(audioList: List<Audio>): List<Audio>
}