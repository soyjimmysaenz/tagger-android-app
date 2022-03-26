package me.taggerapp.android.taggedItems.home

import me.taggerapp.android.taggedItems.domain.GetTaggedItems
import me.taggerapp.android.taggedItems.domain.TaggedItem

class HomeController(
    private val getTaggedItems: GetTaggedItems
) {
    private var currentTaggedItems: List<TaggedItem> = emptyList()

    suspend fun loadItems(): List<TaggedItem> {
        currentTaggedItems = getTaggedItems()
        return currentTaggedItems
    }
}

val TaggedItem.ratingText: String
get() {
    if (this.rating < 0 || this.rating > 5) return "?⭐️"
    return "${this.rating}⭐️"
}