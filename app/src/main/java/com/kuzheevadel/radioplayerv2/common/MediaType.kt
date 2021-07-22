package com.kuzheevadel.radioplayerv2.common

sealed class MediaType<out R> {
    data class None(val isLoaded: Boolean): MediaType<Nothing>()
    data class Track<out T>(val track: T): MediaType<T>()
    data class RadioStation<out T>(val radioStation: T): MediaType<T>()
}