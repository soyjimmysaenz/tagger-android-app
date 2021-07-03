package me.taggerapp.android.taggedItems

import java.util.*
import kotlin.random.Random

interface TaggedItemsRepository {
    fun getAll(): List<TaggedItem>
}

class TaggedItemsRepositoryImpl(
    //TODO: reemplazar con implementacion en BD local y/o remota
    private val dataSource: SampleTaggedItemsDataSource
) : TaggedItemsRepository {

    override fun getAll(): List<TaggedItem> {
        return dataSource.getAll()
    }
}

class SampleTaggedItemsDataSource {

    companion object {
        private const val LIST_LENGTH = 20
    }

    fun getAll(): List<TaggedItem> {
        val list = mutableListOf<TaggedItem>()
        for (counter in 1..LIST_LENGTH) {
            val id = UUID.randomUUID().toString()
            val newModel = TaggedItem(
                id = id,
                title = "My item $id",
                description = "Lorem Ipsum $id",
                rating = Random.Default.nextInt(1, 5),
                imagePath = null,
                createdAt = Date().time
            )
            list.add(newModel)
        }
        return list
    }
}

