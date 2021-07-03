package me.taggerapp.android.taggedItems.home

import me.taggerapp.android.taggedItems.GetTaggedItems
import me.taggerapp.android.taggedItems.TaggedItem

class HomeController(
    val getTaggedItems: GetTaggedItems
) {
    private var currentTaggedItems: List<TaggedItem> = emptyList()

    fun loadItems(): List<TaggedItem> {
        currentTaggedItems = getTaggedItems()
        return currentTaggedItems
    }
}