package com.kuzheevadel.core.database

import androidx.room.TypeConverter
import com.kuzheevadel.core.common.Constants.EMPTY_LIST

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