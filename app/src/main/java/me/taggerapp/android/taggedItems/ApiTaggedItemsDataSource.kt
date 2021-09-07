package me.taggerapp.android.taggedItems

import io.ktor.client.request.*
import me.taggerapp.android.providers.networking.KtorProviderFactory

class ApiTaggedItemsDataSource(
    networkFactory: KtorProviderFactory
) {
    private val apiClient = networkFactory.createClient()

    companion object {
        private const val TAGGED_ITEMS_RESOURCE = "taggedItems"
    }

    suspend fun getAll(): List<TaggedItemDto> = apiClient.get(TAGGED_ITEMS_RESOURCE)
}