package com.kuzheevadel.radioplayerv2.database

import androidx.room.TypeConverter

const val EMPTY_LIST = "Empty"

class PlaylistInfoConverter {

    @TypeConverter
    fun fromList(list: List<String>): String {
        return if (list.isEmpty()) {
            EMPTY_LIST
        } else {
            list.joinToString(",")
        }
    }

    @TypeConverter
    fun toList(idString: String): List<String> {
        return if (idString == EMPTY_LIST) {
            emptyList()
        } else {
            idString.split(",")
        }
    }
}