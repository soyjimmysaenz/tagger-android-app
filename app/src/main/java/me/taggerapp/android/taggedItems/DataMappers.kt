package me.taggerapp.android.taggedItems

import com.google.gson.Gson
import me.taggerapp.android.helpers.tokenListType

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

fun parseToTaggedItems(stringResponse: String): List<TaggedItemDto> {
    val type = tokenListType<TaggedItemDto>()
    return Gson().fromJson(stringResponse, type)
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