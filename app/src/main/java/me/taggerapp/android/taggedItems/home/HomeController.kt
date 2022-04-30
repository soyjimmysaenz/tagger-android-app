package me.taggerapp.android.taggedItems.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import me.taggerapp.android.taggedItems.domain.CreateDefaultTaggedItem
import me.taggerapp.android.taggedItems.domain.GetTaggedItems
import me.taggerapp.android.taggedItems.domain.TaggedItem

class HomeController(
    private val getTaggedItems: GetTaggedItems,
    private val createDefaultTaggedItem: CreateDefaultTaggedItem,
) {
    private var currentTaggedItems: List<TaggedItem> = emptyList()

    fun loadItems(): Flow<List<TaggedItem>> {
        return getTaggedItems()
            .onEach { taggedItems ->
                currentTaggedItems = taggedItems
            }
    }

    suspend fun addAnItem(): TaggedItem {
        val newTaggedItem = createDefaultTaggedItem()
        return newTaggedItem
    }
}

val TaggedItem.ratingText: String
get() {
    if (this.rating < 0 || this.rating > 5) return "?⭐️"
    return "${this.rating}⭐️"
}