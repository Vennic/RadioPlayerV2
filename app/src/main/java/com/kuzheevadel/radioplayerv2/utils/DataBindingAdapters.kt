package com.kuzheevadel.radioplayerv2.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.kuzheevadel.radioplayerv2.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

@BindingAdapter(value = ["app:url"])
fun setRoundedCornersImage(view: ImageView, uri: Uri?) {
    Picasso.get()
            .load(uri)
            .fit()
            .placeholder(R.drawable.album_art_default)
            .transform(RoundedCornersTransformation(20, 3))
            .into(view)
}

@BindingAdapter(value = ["app:large_url"])
fun setLargeRoundedCornersImage(view: ImageView, uri: Uri?) {
        Picasso.get()
                .load(uri)
                .fit()
                .placeholder(R.drawable.album_art_default)
                .transform(RoundedCornersTransformation(40, 6))
                .into(view)
}

@BindingAdapter(value = ["app:album_url"])
fun loadImage(view: ImageView, uri: Uri?) {
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