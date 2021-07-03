package me.taggerapp.android.taggedItems

class GetTaggedItems(private val taggedItemsRepository: TaggedItemsRepository) {

    operator fun invoke(): List<TaggedItem> {
        return taggedItemsRepository
            .getAll()
            .sortedBy { item ->
                item.title
            }
    }
}