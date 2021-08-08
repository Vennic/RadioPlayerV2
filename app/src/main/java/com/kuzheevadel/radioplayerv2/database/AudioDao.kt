package com.kuzheevadel.radioplayerv2.database

import androidx.room.Dao
import androidx.room.Query
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.models.AudioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AudioDao {

    @Query("SELECT * FROM audioentity WHERE id = :audioId")
    suspend fun getAudio(audioId: Int): AudioEntity
}