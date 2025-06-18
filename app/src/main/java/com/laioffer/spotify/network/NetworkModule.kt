package com.laioffer.spotify.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// static
object NetworkModule {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    val retrofit: Retrofit =Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()


    val networkApi = retrofit.create(NetworkApi::class.java)
}