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

class SaveGeneratedTaggedItem(private val taggedItemsRepository: TaggedItemsRepository) {

    operator fun invoke(): TaggedItem? {
        val generatedModel = taggedItemsRepository.buildRandom()
        val isSaved = taggedItemsRepository.save(generatedModel)
        if (!isSaved) return null
        return generatedModel
    }
}