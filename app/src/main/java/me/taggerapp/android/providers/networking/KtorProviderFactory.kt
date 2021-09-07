package me.taggerapp.android.providers.networking

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import me.taggerapp.android.startup.FlipperBootstrapper

object KtorProviderFactory {

    fun createClient(
        baseApiHost: String = ApiConstants.BASE_HOST,
        baseApiProtocol: URLProtocol = ApiConstants.BASE_PROTOCOL_TYPED,
        clientEngine: HttpClientEngineFactory<OkHttpConfig> = OkHttp
    ): HttpClient {
        return HttpClient(clientEngine) {
            engine {
                FlipperBootstrapper.buildOkhttpInterceptor()?.let { interceptor ->
                    addNetworkInterceptor(interceptor)
                }
            }
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
            defaultRequest {
                host = baseApiHost
                url.protocol = baseApiProtocol
            }
        }
    }

}