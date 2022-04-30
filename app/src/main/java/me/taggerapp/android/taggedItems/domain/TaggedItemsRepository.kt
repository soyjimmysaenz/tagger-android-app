package me.taggerapp.android.taggedItems.domain

import kotlinx.coroutines.flow.Flow

interface TaggedItemsRepository {
    suspend fun getAll(): List<TaggedItem>
    fun getAllAsFlow(): Flow<List<TaggedItem>>
    suspend fun save(taggedItem: TaggedItem): Boolean
    fun generateId(): String
    fun generateNowMillis(): Long
}