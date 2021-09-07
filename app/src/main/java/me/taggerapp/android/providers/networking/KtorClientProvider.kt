package me.taggerapp.android.providers.networking

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class KtorClientProvider(
    clientFactory: KtorProviderFactory = KtorProviderFactory
) : AsyncHttpClientProvider {

    private val client = clientFactory.createClient()

    override suspend fun requestString(resource: String): String {
        val httpResponse: HttpResponse = client.get(resource)
        return httpResponse.receive()
    }
}