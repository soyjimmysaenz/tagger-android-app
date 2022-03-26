package me.taggerapp.android.taggedItems.domain

class GetTaggedItems(private val taggedItemsRepository: TaggedItemsRepository) {

    operator fun invoke(): List<TaggedItem> {
        return taggedItemsRepository
            .getAllSync()
            .sortedBy { item ->
                item.title
            }
    }
}