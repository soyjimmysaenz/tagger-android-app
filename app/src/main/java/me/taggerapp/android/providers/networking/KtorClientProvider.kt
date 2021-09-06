package me.taggerapp.android.providers.networking

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class KtorClientProvider(
    private val baseApiUrl: String = ApiConstants.BASE_URL,
    clientEngine: HttpClientEngineFactory<*> = Android
) : AsyncHttpClientProvider {

    private val client = HttpClient(clientEngine)

    override suspend fun requestString(resource: String): String {
        val absUrl = "$baseApiUrl/$resource"
        val httpResponse: HttpResponse = client.get(absUrl)
        return httpResponse.receive()
    }
}