package me.taggerapp.android.taggedItems

import me.taggerapp.android.taggedItems.home.HomeController

object ModuleFactory {
    internal fun getHomeController(): HomeController {
        val dataSource = SampleTaggedItemsDataSource()
        val repository: TaggedItemsRepository = TaggedItemsRepositoryImpl(dataSource)
        val getTaggedItems = GetTaggedItems(repository)
        return HomeController(getTaggedItems)
    }
}