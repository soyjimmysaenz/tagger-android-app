package me.taggerapp.android.taggedItems

fun TaggedItemEntity.toDomain(): TaggedItem {
    return TaggedItem(
        id = this.id,
        title = this.title,
        description = this.description,
        rating = this.rating,
        imagePath = this.imagePath,
        createdAt = this.createdAt,
        source = ItemSource.LOCAL
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

fun TaggedItemDto.toDomain(): TaggedItem =
    TaggedItem(
        id = id,
        title = title,
        description = description,
        rating = rating,
        imagePath = imagePath,
        createdAt = createdAt,
        source = ItemSource.REMOTE
    )