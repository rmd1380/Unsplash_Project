package com.example.unsplashproject.module

import com.example.unsplashproject.repositories.Repository
import com.example.unsplashproject.services.ServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(api:ServiceApi)=Repository(api)
}