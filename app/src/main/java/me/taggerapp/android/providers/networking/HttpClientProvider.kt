package me.taggerapp.android.providers.networking

import io.ktor.http.*

object ApiConstants {
    const val BASE_HOST = "tagger-mock-api.herokuapp.com"
    @Suppress("MemberVisibilityCanBePrivate")
    const val BASE_PROTOCOL = "https"
    val BASE_PROTOCOL_TYPED = URLProtocol.HTTPS
    const val BASE_URL = "$BASE_PROTOCOL://$BASE_HOST"
}

interface SyncHttpClientProvider {
    fun requestString(resource: String): String
}

interface AsyncHttpClientProvider {
    suspend fun requestString(resource: String): String
}