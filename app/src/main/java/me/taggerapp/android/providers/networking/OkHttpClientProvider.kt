package me.taggerapp.android.providers.networking

import me.taggerapp.android.startup.FlipperBootstrapper
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class OkHttpClientProvider(
    private val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder(),
    private val baseApiUrl: String = ApiConstants.BASE_URL
) : SyncHttpClientProvider {

    private val client: OkHttpClient =
        FlipperBootstrapper.buildOkhttpInterceptor()?.let { interceptor ->
            clientBuilder
                .addNetworkInterceptor(interceptor)
                .build()
        } ?: run {
            clientBuilder
                .build()
        }

    fun getCurrentClient() = client

    override fun requestString(resource: String): String {
        val absUrl = "$baseApiUrl/$resource"
        val request = Request.Builder()
            .url(absUrl)
            .build()

        client.newCall(request).execute().use { httpResponse ->
            if (!httpResponse.isSuccessful) {
                throw IOException("La solicitud HTTP ha fallado | $httpResponse")
            }
            val responseBody = httpResponse.body ?: run {
                throw IOException("La respuesta HTTP obtenida es inválida")
            }
            return responseBody.string()
        }
    }
}