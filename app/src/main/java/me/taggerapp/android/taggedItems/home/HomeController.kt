package me.taggerapp.android.taggedItems.home

import me.taggerapp.android.taggedItems.GetTaggedItems
import me.taggerapp.android.taggedItems.SaveGeneratedTaggedItem
import me.taggerapp.android.taggedItems.TaggedItem

class HomeController(
    private val getTaggedItems: GetTaggedItems,
    private val saveGeneratedTaggedItem: SaveGeneratedTaggedItem
) {
    private val currentTaggedItems: MutableList<TaggedItem> = mutableListOf()

    fun loadItems(): List<TaggedItem> {
        currentTaggedItems.clear()
        val loadedItems = getTaggedItems()
        currentTaggedItems.addAll(loadedItems)
        return currentTaggedItems
    }

    fun addTaggedItem(): Pair<TaggedItem?, List<TaggedItem>> {
        val lastTaggedItem = saveGeneratedTaggedItem()
        if (lastTaggedItem != null) {
            currentTaggedItems.add(lastTaggedItem)
        }
        return Pair(lastTaggedItem, currentTaggedItems)
    }
}

val TaggedItem.ratingText: String
get() {
    if (this.rating < 0 || this.rating > 5) return "?⭐️"
    return "${this.rating}⭐️"
}