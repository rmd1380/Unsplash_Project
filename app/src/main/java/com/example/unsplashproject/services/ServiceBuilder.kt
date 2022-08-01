package com.example.unsplashproject.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private const val URL = "https://api.unsplash.com/"

    //OkHTTP Client
    private val okHttp= OkHttpClient.Builder()

    //Retrofit Builder
    private val builder= Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())
    //Retrofit Instance
    private val retrofit: Retrofit = builder.build()

    fun <T> buildService(serviceType:Class<T>): T {
        return retrofit.create(serviceType)
    }
}