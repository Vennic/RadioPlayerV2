package com.kuzheevadel.radioplayerv2.tracks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.radioplayerv2.common.QueryResult
import com.kuzheevadel.radioplayerv2.models.Track
import com.kuzheevadel.radioplayerv2.repositories.TracksRepositoryInterface
import com.kuzheevadel.radioplayerv2.tracks.di.TracksFragmentScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@TracksFragmentScope
class TracksViewModel @Inject constructor(
    private val tracksRepo: TracksRepositoryInterface
): ViewModel() {

    private val _loadState =
            MutableStateFlow<QueryResult<List<Track>>>(QueryResult.Loading(false))
    val loadState: StateFlow<QueryResult<List<Track>>> = _loadState

    fun onPermissionGranted() {
        viewModelScope.launch {
            _loadState.value = QueryResult.Loading(true)

            try {
                tracksRepo.loadTracksFromStorage()
            } catch (error: Throwable) {
                _loadState.value = QueryResult.Error(error)
                _loadState.value = QueryResult.Loading(false)
            }

            _loadState.value = QueryResult.Loading(false)
            _loadState.value = QueryResult.Success(tracksRepo.getAllTracks())

            Log.d("ASDF", tracksRepo.getAllTracks().toString())
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("ASDC", "TracksViewModel onCleared")
    }
}