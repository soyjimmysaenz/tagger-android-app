package me.taggerapp.android.core.data.database.population

import me.taggerapp.android.core.data.database.DefaultAppDatabase
import me.taggerapp.android.core.data.database.entities.TaggedItemEntity
import java.util.*
import kotlin.random.Random

object DefaultPopulator {

    fun populateWithSampleTaggedItems(database: DefaultAppDatabase) {
        database.taggedItemDao().insertSync(buildTaggedItems(20))
    }

    @Suppress("SameParameterValue")
    private fun buildTaggedItems(size: Int): List<TaggedItemEntity> =
        mutableListOf<TaggedItemEntity>().apply {
            for (position in 1..size) {
                add(buildTaggedItem())
            }
        }

    private fun buildTaggedItem(): TaggedItemEntity {
        val id = UUID.randomUUID().toString()
        return TaggedItemEntity(
            id = id,
            title = "My item $id",
            description = "Lorem Ipsum $id",
            rating = Random.Default.nextInt(1, 5),
            imagePath = null,
            createdAt = Date().time
        )
    }
}