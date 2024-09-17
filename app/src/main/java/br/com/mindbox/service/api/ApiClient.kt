package br.com.mindbox.service.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun createAuthClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.105:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun createClient(token: String): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(token))
            .build()

        return Retrofit.Builder()
            .baseUrl("http://192.168.0.105:8080/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
