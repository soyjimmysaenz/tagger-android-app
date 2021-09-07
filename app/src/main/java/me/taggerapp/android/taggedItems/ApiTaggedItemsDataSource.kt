package me.taggerapp.android.taggedItems

class ApiTaggedItemsDataSource(
    private val itemsService: TaggedItemsService
) {
    suspend fun getAll(): List<TaggedItemDto> = itemsService.getAll()
}