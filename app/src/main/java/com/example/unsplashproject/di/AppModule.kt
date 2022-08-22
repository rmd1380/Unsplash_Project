package com.example.unsplashproject.di

import com.example.unsplashproject.repositories.RepositoryFeed
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
    fun provideRepository(api:ServiceApi)=RepositoryFeed(api)

}