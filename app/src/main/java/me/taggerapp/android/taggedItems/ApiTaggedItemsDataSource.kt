package me.taggerapp.android.taggedItems

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.taggerapp.android.providers.networking.SyncHttpClientProvider

class ApiTaggedItemsDataSource(
    private val httpClient: SyncHttpClientProvider,
    private val workDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    companion object {
        private const val TAGGED_ITEMS_RESOURCE = "taggedItems"
    }

    suspend fun getAll(): List<TaggedItemDto> {
        return withContext(workDispatcher) {
            val stringResponse = httpClient.requestString(TAGGED_ITEMS_RESOURCE)
            parseToTaggedItems(stringResponse)
        }
    }
}