package com.kuzheevadel.radioplayerv2.audio.detailaudiolist

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.common.DetailAudioItem
import com.kuzheevadel.radioplayerv2.models.Audio
import kotlinx.coroutines.flow.Flow

abstract class BaseDetailAudioViewModel: ViewModel() {

    lateinit var audioFlow: Flow<List<Audio>>


    abstract fun getDetailItem(): DetailAudioItem

    abstract fun init(position: Int)

    abstract fun onAudioClicked(position: Int)

    abstract fun getAudioList(): List<Audio>
}