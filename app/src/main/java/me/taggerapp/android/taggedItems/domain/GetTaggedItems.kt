package me.taggerapp.android.taggedItems.domain

class GetTaggedItems(private val taggedItemsRepository: TaggedItemsRepository) {

    suspend operator fun invoke(): List<TaggedItem> {
        return taggedItemsRepository
            .getAll()
            .sortedBy { item ->
                item.title
            }
    }
}