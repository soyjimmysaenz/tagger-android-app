package me.taggerapp.android.providers

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import coil.load
import coil.transform.CircleCropTransformation

object MediaProvider {

    fun loadInto(imageView: AppCompatImageView, url: String, @DrawableRes placeholderId: Int) {
        imageView.load(url) {
            crossfade(true)
            placeholder(placeholderId)
            transformations(CircleCropTransformation())
        }
    }

    fun loadInto(imageView: AppCompatImageView, @DrawableRes imageId: Int) {
        imageView.load(imageId) {
            transformations(CircleCropTransformation())
        }
    }
}