package me.taggerapp.android.taggedItems

import retrofit2.http.GET

interface TaggedItemsService {
    @GET("taggedItems")
    suspend fun getAll(): List<TaggedItemDto>
}