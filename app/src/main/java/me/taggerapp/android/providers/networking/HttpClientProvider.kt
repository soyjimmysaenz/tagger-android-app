package me.taggerapp.android.providers.networking

object ApiConstants {
    const val BASE_URL = "https://tagger-mock-api.herokuapp.com"
}

interface SyncHttpClientProvider {
    fun requestString(resource: String): String
}