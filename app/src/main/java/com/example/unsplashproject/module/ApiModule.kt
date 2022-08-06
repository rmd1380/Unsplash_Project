package com.example.unsplashproject.module

import com.example.unsplashproject.services.ServiceApi
import com.example.unsplashproject.services.ServiceBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val URL = "https://api.unsplash.com/"

    @Provides
    @Singleton
    fun provideApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun serviceApi(): ServiceApi {
        return provideApi().create(ServiceApi::class.java)
    }
}