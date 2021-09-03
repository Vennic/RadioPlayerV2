package com.kuzheevadel.audio.common

sealed class DatabaseState{
    data class AudioAdded(val audioName: String, val playlistName: String): DatabaseState()
    data class Error(val error: String): DatabaseState()
    data class None(val t: String): DatabaseState()
}