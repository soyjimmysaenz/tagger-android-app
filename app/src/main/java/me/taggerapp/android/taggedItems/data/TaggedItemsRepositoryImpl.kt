package me.taggerapp.android.taggedItems.data

import me.taggerapp.android.core.data.database.daos.TaggedItemDao
import me.taggerapp.android.taggedItems.domain.TaggedItem
import me.taggerapp.android.taggedItems.domain.TaggedItemsRepository
import java.util.*

class TaggedItemsRepositoryImpl(
    private val taggedItemDao: TaggedItemDao,
) : TaggedItemsRepository {

    override fun getAllSync(): List<TaggedItem> {
        return taggedItemDao
            .getAllSync()
            .map { entity -> entity.toDomain() }
    }

    override fun saveSync(taggedItem: TaggedItem): Boolean {
        val entity = taggedItem.toEntity()
        taggedItemDao.insertSync(entity)
        return true
    }

    override fun generateId(): String = UUID.randomUUID().toString()

    override fun generateNowMillis(): Long = Date().time
}