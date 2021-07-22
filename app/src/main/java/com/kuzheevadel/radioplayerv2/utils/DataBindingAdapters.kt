package com.kuzheevadel.radioplayerv2.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.kuzheevadel.radioplayerv2.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

@BindingAdapter(value = ["app:url"])
fun setRoundedCornersImage(view: ImageView, uri: Uri) {
    Picasso.get()
            .load(uri)
            .fit()
            .placeholder(R.drawable.album_art_default)
            .transform(RoundedCornersTransformation(20, 3))
            .into(view)
}