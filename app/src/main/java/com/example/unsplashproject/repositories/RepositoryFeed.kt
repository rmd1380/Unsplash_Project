package com.example.unsplashproject.repositories

import com.example.unsplashproject.model.response.FeedPhotoResponse
import com.example.unsplashproject.services.Resource
import com.example.unsplashproject.services.ServiceApi
import javax.inject.Inject

class RepositoryFeed @Inject constructor(private val api: ServiceApi) : BaseRepository() {


    suspend fun getPhotoDetailById(id: String): Resource<FeedPhotoResponse> {
        return safeApiCall { api.getPhotoDetailById(id) }
    }

    suspend fun getUserByUsername(userName: String): Resource<FeedPhotoResponse> {
        return safeApiCall { api.getUserByUsername(userName) }
    }


}