package me.taggerapp.android.taggedItems

import me.taggerapp.android.helpers.DateHelper

interface TaggedItemsRepository {
    suspend fun getAll(): List<TaggedItem>
    suspend fun save(taggedItem: TaggedItem): Boolean
    fun generateId(): String
    fun generateNowMillis(): Long
}

class TaggedItemsRepositoryImpl(
    private val localDataSource: DatabaseTaggedItemsDataSource,
    private val apiDataSource: ApiTaggedItemsDataSource
) : TaggedItemsRepository {

    override suspend fun getAll(): List<TaggedItem> {
        val remoteItems = apiDataSource
            .getAll()
            .map { dto -> dto.toDomain() }

        val localItems = localDataSource
            .getAll()
            .map { entity -> entity.toDomain() }

        return remoteItems + localItems
    }

    override suspend fun save(taggedItem: TaggedItem): Boolean {
        val entity = taggedItem.toEntity()
        localDataSource.insert(entity)
        return true
    }

    override fun generateId(): String = java.util.UUID.randomUUID().toString()

    override fun generateNowMillis(): Long = DateHelper.now()
}

