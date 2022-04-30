package me.taggerapp.android.taggedItems.domain

import java.util.*
import kotlin.random.Random

class CreateDefaultTaggedItem(
    private val repository: TaggedItemsRepository,
) {

    suspend operator fun invoke(): TaggedItem {
        val defaultTaggedItem = buildDefaultTaggedItem()
        val wasSaved: Boolean = try {
            repository.save(defaultTaggedItem)
        } catch (unknownException: Exception) {
            throw InvalidTaggedItemException(unknownException.message, unknownException)
        }
        if (!wasSaved) {
            throw  InvalidTaggedItemException("Repository could not save the taggedItem", null)
        }
        return defaultTaggedItem
    }

    private fun buildDefaultTaggedItem(): TaggedItem {
        val id = uuid()
        return TaggedItem(
            id = id,
            title = "Title $id",
            description = "Description $id",
            rating = Random.nextInt(1, 5),
            imagePath = null,
            createdAt = now(),
        )
    }

    private fun uuid() = UUID.randomUUID().toString()

    private fun now() = System.currentTimeMillis()
}