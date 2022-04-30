package me.taggerapp.android.taggedItems.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTaggedItems(private val taggedItemsRepository: TaggedItemsRepository) {

    operator fun invoke(): Flow<List<TaggedItem>> {
        return taggedItemsRepository
            .getAllAsFlow()
            .map { taggedItems ->
                taggedItems.sortedBy { item ->
                    item.title
                }
            }
    }
}