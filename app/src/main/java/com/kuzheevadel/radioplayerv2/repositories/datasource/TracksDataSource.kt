package com.kuzheevadel.radioplayerv2.repositories.datasource

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.kuzheevadel.radioplayerv2.common.Constants
import com.kuzheevadel.radioplayerv2.models.Track
import javax.inject.Inject

class TracksDataSource @Inject constructor(
    private val context: Context): TracksDataSourceInterface {

    override suspend fun getTracksFromStorage(): List<Track> {
        val tracksList = mutableListOf<Track>()

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

            val albumIdColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val title = cursor.getString(titleColumn)
                val albumId = cursor.getLong(albumIdColumn)

                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                val imageUri: Uri = ContentUris.withAppendedId(Uri.parse(Constants.BASE_ALBUMSART_URI), albumId)

                tracksList += Track(contentUri, name, id, title, albumId, imageUri)
            }
        }

        return tracksList
    }

}