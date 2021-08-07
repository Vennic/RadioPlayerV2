package com.kuzheevadel.radioplayerv2.database

import androidx.room.Dao
import androidx.room.Query
import com.kuzheevadel.radioplayerv2.models.Audio
import kotlinx.coroutines.flow.Flow

@Dao
interface AudioDao {

    @Query("SELECT * FROM audio WHERE id = :audioId")
    fun getAudio(audioId: Int): Flow<Audio>
}