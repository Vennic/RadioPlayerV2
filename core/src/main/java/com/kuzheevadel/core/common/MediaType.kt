package com.kuzheevadel.core.common

sealed class MediaType<out R> {
    data class None(val isLoaded: Boolean): MediaType<Nothing>()
    data class Audio<out T>(val audio: T): MediaType<T>()
    data class RadioStation<out T>(val radioStation: T): MediaType<T>()
}