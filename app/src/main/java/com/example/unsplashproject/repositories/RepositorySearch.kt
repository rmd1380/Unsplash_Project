package com.example.unsplashproject.repositories

import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.model.response.SearchResponse
import com.example.unsplashproject.model.sitesearchmodel.Results
import com.example.unsplashproject.services.Resource
import com.example.unsplashproject.services.ServiceApi
import javax.inject.Inject

class RepositorySearch @Inject constructor(private val api: ServiceApi) : BaseRepository() {


    suspend fun getPhotosBySearch(query: String): Resource<SearchResponse> {
        return safeApiCall { api.getPhotosBySearch(query) }
    }

    suspend fun getUsersBySearch(query: String): Resource<List<SearchResponse>> {
        return safeApiCall { api.getUsersBySearch(query) }
    }
}