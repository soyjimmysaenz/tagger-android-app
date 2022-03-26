package me.taggerapp.android.taggedItems.data

import me.taggerapp.android.core.data.database.entities.TaggedItemEntity
import me.taggerapp.android.taggedItems.domain.TaggedItem

fun TaggedItemEntity.toDomain(): TaggedItem {
    return TaggedItem(
        id = this.id,
        title = this.title,
        description = this.description,
        rating = this.rating,
        imagePath = this.imagePath,
        createdAt = this.createdAt,
    )
}

fun TaggedItem.toEntity(): TaggedItemEntity {
    return TaggedItemEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        rating = this.rating,
        imagePath = this.imagePath,
        createdAt = this.createdAt
    )
}