package com.kuzheevadel.radioplayerv2.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kuzheevadel.radioplayerv2.R
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["app:glide_url"])
fun loadImage(view: ImageView, uri: Uri?) {
        Glide.with(view)
                .load(uri)
                .placeholder(R.drawable.album_art_default)
                .into(view)
}

@BindingAdapter(value = ["app:album_url"])
fun loadImagew(view: ImageView, uri: Uri?) {
        val stringUri = uri.toString()

        if (stringUri.startsWith("content", true)) {
                Picasso.get().load(uri)
                        .fit()
                        .placeholder(R.drawable.album_art_default)
                        .into(view)
        } else {
                try {
                        Picasso.get().load(uri.toString())
                                .fit()
                                .placeholder(R.drawable.album_art_default)
                                .into(view)
                } catch (e: Exception) {
                        view.setImageResource(R.drawable.album_art_default)
                }

        }
}