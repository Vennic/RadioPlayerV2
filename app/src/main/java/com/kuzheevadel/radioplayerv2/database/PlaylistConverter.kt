package com.kuzheevadel.radioplayerv2.database

import androidx.room.TypeConverter

class PlaylistConverter {

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toList(idString: String): List<String> {
        return idString.split(",")
    }
}