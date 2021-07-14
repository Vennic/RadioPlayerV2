package com.kuzheevadel.radioplayerv2.common


sealed class QueryResult<out R> {
    data class Success<out T>(val data: T) : QueryResult<T>()
    data class Error(val error: Throwable) : QueryResult<Nothing>()
    data class Loading(val isLoading: Boolean) : QueryResult<Nothing>()
}