package me.taggerapp.android.taggedItems

import java.util.*
import kotlin.random.Random

class SampleTaggedItemsDataSource {

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