package me.taggerapp.android.taggedItems

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//ver https://developer.android.com/kotlin/parcelize?hl=es-419#groovy
@Parcelize
data class TaggedItem(
    val id: String,
    val title: String,
    val description: String?,
    val rating: Int,
    val imagePath: String?,
    val createdAt: Long
) : Parcelable