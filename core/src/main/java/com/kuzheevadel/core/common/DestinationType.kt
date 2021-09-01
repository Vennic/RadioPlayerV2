package com.kuzheevadel.core.common

sealed class DestinationType<out R> {
    data class AllAudio(val audioPos: Int = 0): DestinationType<Nothing>()
    data class Album(val audioPos: Int = 0, val albumPos: Int = 0): DestinationType<Nothing>()
    data class Playlist(val audioPos: Int = 0, val playlistPos: Int = 0): DestinationType<Nothing>()
}