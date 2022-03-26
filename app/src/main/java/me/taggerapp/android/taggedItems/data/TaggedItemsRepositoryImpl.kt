package me.taggerapp.android.taggedItems.data

import me.taggerapp.android.core.data.database.daos.TaggedItemDao
import me.taggerapp.android.taggedItems.domain.TaggedItem
import me.taggerapp.android.taggedItems.domain.TaggedItemsRepository
import java.util.*

class TaggedItemsRepositoryImpl(
    private val taggedItemDao: TaggedItemDao,
) : TaggedItemsRepository {

    override suspend fun getAll(): List<TaggedItem> {
        return taggedItemDao
            .getAll()
            .map { entity -> entity.toDomain() }
    }

    override suspend fun save(taggedItem: TaggedItem): Boolean {
        val entity = taggedItem.toEntity()
        taggedItemDao.insert(entity)
        return true
    }

    override fun generateId(): String = UUID.randomUUID().toString()

    override fun generateNowMillis(): Long = Date().time
}