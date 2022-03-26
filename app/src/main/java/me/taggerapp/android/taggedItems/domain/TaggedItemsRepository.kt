package me.taggerapp.android.taggedItems.domain

interface TaggedItemsRepository {
    fun getAllSync(): List<TaggedItem>
    fun saveSync(taggedItem: TaggedItem): Boolean
    fun generateId(): String
    fun generateNowMillis(): Long
}