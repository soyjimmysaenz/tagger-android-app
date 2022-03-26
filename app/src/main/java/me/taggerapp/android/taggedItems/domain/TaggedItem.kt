package me.taggerapp.android.taggedItems.domain

data class TaggedItem(
    val id: String,
    val title: String,
    val description: String?,
    val rating: Int,
    val imagePath: String?,
    val createdAt: Long
)