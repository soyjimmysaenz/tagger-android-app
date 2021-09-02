package me.taggerapp.android.providers.networking

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class OkHttpClientProvider(
    private val client: OkHttpClient = OkHttpClient(),
    private val baseApiUrl: String = ApiConstants.BASE_URL
): SyncHttpClientProvider {

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