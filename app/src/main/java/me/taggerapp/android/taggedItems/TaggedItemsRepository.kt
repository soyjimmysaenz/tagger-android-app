package me.taggerapp.android.taggedItems

import me.taggerapp.android.helpers.DateHelper

interface TaggedItemsRepository {
    suspend fun getAll(): List<TaggedItem>
    suspend fun save(taggedItem: TaggedItem): Boolean
    //TODO: remover, creado para efectos prácticos
    fun buildRandom(): TaggedItem
    fun generateId(): String
    fun generateNowMillis(): Long
}

class TaggedItemsRepositoryImpl(
    private val localDataSource: DatabaseTaggedItemsDataSource,
    //TODO: reemplazar con implementacion en BD local y/o remota
    private val sampleDataSource: SampleTaggedItemsDataSource
) : TaggedItemsRepository {

    override suspend fun getAll(): List<TaggedItem> {
        return localDataSource
            .getAll()
            .map { entity -> entity.toDomain() }
    }

    override suspend fun save(taggedItem: TaggedItem): Boolean {
        val entity = taggedItem.toEntity()
        localDataSource.insert(entity)
        return true
    }

    override fun buildRandom() = sampleDataSource.buildModel()

    override fun generateId(): String = java.util.UUID.randomUUID().toString()

    override fun generateNowMillis(): Long = DateHelper.now()
}

