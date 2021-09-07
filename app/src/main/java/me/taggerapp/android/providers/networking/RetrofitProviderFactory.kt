package me.taggerapp.android.providers.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProviderFactory {

    fun <TService> createService(serviceClass: Class<TService>): TService {
        val retrofit = create()
        return retrofit.create(serviceClass)
    }

    private fun create(
        baseApiUrl: String = ApiConstants.BASE_URL,
        httpClientProvider: OkHttpClientProvider = OkHttpClientProvider()
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseApiUrl)
            .client(httpClientProvider.getCurrentClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}