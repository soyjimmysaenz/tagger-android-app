package me.taggerapp.android.taggedItems

import android.content.Context
import me.taggerapp.android.providers.networking.OkHttpClientProvider
import me.taggerapp.android.providers.MainDatabase
import me.taggerapp.android.taggedItems.details.SaveTaggedItemController
import me.taggerapp.android.taggedItems.home.HomeController

object ModuleFactory {

    internal fun getHomeController(context: Context): HomeController {
        val repository = provideTaggedItemRepository(context)
        return HomeController(repository, context.applicationContext::getString)
    }

    internal fun getSaveController(context: Context): SaveTaggedItemController {
        val repository = provideTaggedItemRepository(context)
        return SaveTaggedItemController(repository, context.applicationContext)
    }

    private fun provideTaggedItemRepository(context: Context): TaggedItemsRepositoryImpl {
        val itemsDao = MainDatabase.getInstance(context.applicationContext).taggedItemDao()
        val dbSource = DatabaseTaggedItemsDataSource(itemsDao)
        val httpClientProvider = OkHttpClientProvider()
        val apiSource = ApiTaggedItemsDataSource(httpClientProvider)
        return TaggedItemsRepositoryImpl(dbSource, apiSource)
    }
}