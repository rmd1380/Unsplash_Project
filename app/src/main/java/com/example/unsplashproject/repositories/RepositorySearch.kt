package com.example.unsplashproject.repositories

import com.example.unsplashproject.model.response.SearchPhotoResponse
import com.example.unsplashproject.model.response.SearchUserResponse
import com.example.unsplashproject.services.Resource
import com.example.unsplashproject.services.ServiceApi
import javax.inject.Inject

class RepositorySearch @Inject constructor(private val api: ServiceApi) : BaseRepository() {


    suspend fun getPhotosBySearch(query: String): Resource<SearchPhotoResponse> {
        return safeApiCall { api.getPhotosBySearch(query) }
    }

    suspend fun getUsersBySearch(query: String): Resource<SearchUserResponse> {
        return safeApiCall { api.getUsersBySearch(query) }
    }
}