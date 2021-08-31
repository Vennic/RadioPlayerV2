package com.kuzheevadel.core.repositories.datasource

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.kuzheevadel.core.common.Constants
import com.kuzheevadel.core.models.Audio
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AudioDataSourceImp @Inject constructor(
    private val context: Context): AudioDataSource {

    override fun getAudioFlowFromStorage(): Flow<List<Audio>> = flow {
        val audioList = mutableListOf<Audio>()

        val collection =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Audio.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL
                )
            } else {
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            }

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ALBUM_ID
        )

        val query = context.contentResolver.query(
        collection, projection, null, null, null)

        query?.use { cursor ->
            val idColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)

            val nameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)

            val titleColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)

            val albumTitleColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)

            val artistNameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)

            val durationColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)

            val albumIdColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val title = cursor.getString(titleColumn)
                val albumTitle = cursor.getString(albumTitleColumn)
                val artistName = cursor.getString(artistNameColumn)
                val duration = cursor.getInt(durationColumn)
                val albumId = cursor.getLong(albumIdColumn)

                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                val imageUri: Uri = ContentUris.withAppendedId(Uri.parse(Constants.BASE_ALBUMSART_URI), albumId)

                audioList += Audio(contentUri, name, id, title,artistName, albumTitle, duration, albumId, imageUri, isSelected = false, isPlaying = false)
            }
        }
        emit(audioList)
    }

}