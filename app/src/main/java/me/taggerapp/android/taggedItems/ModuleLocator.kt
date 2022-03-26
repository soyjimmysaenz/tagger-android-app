package me.taggerapp.android.taggedItems

import android.content.Context
import me.taggerapp.android.core.data.database.DefaultAppDatabase
import me.taggerapp.android.core.data.database.daos.TaggedItemDao
import me.taggerapp.android.taggedItems.data.TaggedItemsRepositoryImpl
import me.taggerapp.android.taggedItems.domain.GetTaggedItems
import me.taggerapp.android.taggedItems.domain.TaggedItemsRepository
import me.taggerapp.android.taggedItems.home.HomeController

object ModuleLocator {
    internal fun getHomeController(context: Context): HomeController {
        val dataSource: TaggedItemDao = DefaultAppDatabase.getInstance(context).taggedItemDao()
        val repository: TaggedItemsRepository = TaggedItemsRepositoryImpl(dataSource)
        val getTaggedItems = GetTaggedItems(repository)
        return HomeController(getTaggedItems)
    }
}