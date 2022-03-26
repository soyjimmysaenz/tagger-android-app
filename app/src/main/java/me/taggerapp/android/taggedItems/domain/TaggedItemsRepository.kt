package me.taggerapp.android.taggedItems.domain

interface TaggedItemsRepository {
    suspend fun getAll(): List<TaggedItem>
    suspend fun save(taggedItem: TaggedItem): Boolean
    fun generateId(): String
    fun generateNowMillis(): Long
}