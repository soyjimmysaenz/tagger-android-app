package me.taggerapp.android.taggedItems

import java.util.*
import kotlin.random.Random

class SampleTaggedItemsDataSource {

    companion object {
        private const val LIST_LENGTH = 20
    }

    fun getAll(): List<TaggedItem> {
        val list = mutableListOf<TaggedItem>()
        for (counter in 1..LIST_LENGTH) {
            val newModel = buildModel()
            list.add(newModel)
        }
        return list
    }

    fun buildModel(): TaggedItem {
        val id = UUID.randomUUID().toString()
        return TaggedItem(
            id = id,
            title = "My item $id",
            description = "Lorem Ipsum $id",
            rating = Random.Default.nextInt(1, 5),
            imagePath = null,
            createdAt = Date().time
        )
    }
}