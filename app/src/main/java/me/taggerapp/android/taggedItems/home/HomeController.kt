package me.taggerapp.android.taggedItems.home

import me.taggerapp.android.taggedItems.TaggedItem
import me.taggerapp.android.taggedItems.TaggedItemsRepository

class HomeController(
    private val taggedItemsRepository: TaggedItemsRepository,
) {
    private val currentTaggedItems: MutableList<TaggedItem> = mutableListOf()

    suspend fun loadItems(): List<TaggedItem> {
        currentTaggedItems.clear()
        val loadedItems = getTaggedItems()
        currentTaggedItems.addAll(loadedItems)
        return currentTaggedItems
    }

    suspend fun addTaggedItem(): Pair<TaggedItem?, List<TaggedItem>> {
        val lastTaggedItem = saveGeneratedTaggedItem()
        if (lastTaggedItem != null) {
            currentTaggedItems.add(lastTaggedItem)
        }
        return Pair(lastTaggedItem, currentTaggedItems)
    }

    private suspend fun getTaggedItems(): List<TaggedItem> {
        return taggedItemsRepository
            .getAll()
            .sortedBy { item ->
                item.title
            }
    }

    private suspend fun saveGeneratedTaggedItem(): TaggedItem? {
        val generatedModel = taggedItemsRepository.buildRandom()
        val isSaved = taggedItemsRepository.save(generatedModel)
        if (!isSaved) return null
        return generatedModel
    }
}

val TaggedItem.ratingText: String
    get() {
        if (this.rating < 0 || this.rating > 5) return "?⭐️"
        return "${this.rating}⭐️"
    }