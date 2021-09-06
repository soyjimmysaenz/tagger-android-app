package me.taggerapp.android.providers.networking

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import me.taggerapp.android.startup.FlipperBootstrapper

class KtorClientProvider(
    private val baseApiUrl: String = ApiConstants.BASE_URL,
    clientEngine: HttpClientEngineFactory<OkHttpConfig> = OkHttp
) : AsyncHttpClientProvider {

    private val client = HttpClient(clientEngine) {
        engine {
            FlipperBootstrapper.buildOkhttpInterceptor()?.let { interceptor ->
                addNetworkInterceptor(interceptor)
            }
        }
    }

    override suspend fun requestString(resource: String): String {
        val absUrl = "$baseApiUrl/$resource"
        val httpResponse: HttpResponse = client.get(absUrl)
        return httpResponse.receive()
    }
}