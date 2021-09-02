package me.taggerapp.android.taggedItems.home

import android.util.Log
import me.taggerapp.android.R
import me.taggerapp.android.helpers.LoadingDataException
import me.taggerapp.android.providers.networking.NetworkUtils
import me.taggerapp.android.taggedItems.Resources
import me.taggerapp.android.taggedItems.ItemSource
import me.taggerapp.android.taggedItems.TaggedItem
import me.taggerapp.android.taggedItems.TaggedItemsRepository

class HomeController(
    private val taggedItemsRepository: TaggedItemsRepository,
    private val networkUtils: NetworkUtils,
    private val getString: (resourceId: Int) -> String
) {
    companion object {
        private const val TAG = "HomeController"
    }

    private val currentTaggedItems: MutableList<TaggedItem> = mutableListOf()

    suspend fun loadItems(): List<TaggedItem> {
        if (!networkUtils.isNetworkAvailable()) {
            throw buildLoadingItemsException()
        }

        currentTaggedItems.clear()
        val loadedItems = try {
            getTaggedItems()
        } catch (error: Throwable) {
            throw buildLoadingItemsException(error)
        }
        currentTaggedItems.addAll(loadedItems)
        return currentTaggedItems
    }

    private suspend fun getTaggedItems(): List<TaggedItem> {
        return taggedItemsRepository
            .getAll()
            .sortedBy { item ->
                item.title
            }
    }

    private fun buildLoadingItemsException(error: Throwable? = null): LoadingDataException {
        if (error != null) {
            Log.e(TAG, error.message, error)
        }
        val shortErrorMessage = getString(R.string.error_loading_items_short)
        val longErrorMessage = getString(R.string.error_loading_items_long)
        return LoadingDataException(longErrorMessage, shortErrorMessage, longErrorMessage, error)
    }
}

val TaggedItem.ratingText: String
    get() {
        if (this.rating < 0 || this.rating > 5) return "?⭐️"
        return "${this.rating}⭐️"
    }

val TaggedItem.sourceText: String
    get() {
        return when (this.source) {
            ItemSource.REMOTE -> "🌎"
            ItemSource.LOCAL -> "💾"
            ItemSource.MEMORY -> "🔋"
        }
    }

val TaggedItem.imageData: Pair<String, Int>
    get() = Pair(
        if (imagePath.isNullOrEmpty()) Resources.PLACEHOLDER_IMG_PATH else imagePath,
        R.drawable.ic_launcher_foreground
    )