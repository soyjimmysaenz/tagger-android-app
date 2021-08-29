package me.taggerapp.android.taggedItems

import android.content.Context
import me.taggerapp.android.providers.MainDatabase
import me.taggerapp.android.taggedItems.home.HomeController

object ModuleFactory {

    internal fun getHomeController(context: Context): HomeController {
        val itemsDao = MainDatabase.getInstance(context).taggedItemDao()
        val dbSource = DatabaseTaggedItemsDataSource(itemsDao)
        val sampleSource = SampleTaggedItemsDataSource()
        val repository: TaggedItemsRepository = TaggedItemsRepositoryImpl(dbSource, sampleSource)
        return HomeController(repository)
    }
}