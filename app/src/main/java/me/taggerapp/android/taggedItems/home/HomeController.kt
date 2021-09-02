package me.taggerapp.android.taggedItems.home

import me.taggerapp.android.taggedItems.ItemSource
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

    private suspend fun getTaggedItems(): List<TaggedItem> {
        return taggedItemsRepository
            .getAll()
            .sortedBy { item ->
                item.title
            }
    }
}

val TaggedItem.ratingText: String
    get() {
        if (this.rating < 0 || this.rating > 5) return "?⭐️"
        return "${this.rating}⭐️"
    }

val TaggedItem.sourceText: String
    get() {
        return when (this.source) {
            ItemSource.REMOTE -> "🌎"
            ItemSource.LOCAL -> "💾"
            ItemSource.MEMORY -> "🔋"
        }
    }