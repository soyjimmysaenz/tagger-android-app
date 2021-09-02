package me.taggerapp.android.taggedItems

import com.google.gson.Gson
import me.taggerapp.android.helpers.tokenListType

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
    )