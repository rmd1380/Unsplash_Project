package com.example.unsplashproject.repositories


import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.services.Resource
import com.example.unsplashproject.services.ServiceApi
import javax.inject.Inject

class RepositoryFeed @Inject constructor(private val api: ServiceApi) : BaseRepository() {


    suspend fun getPhoto(): Resource<List<PhotoResponse>> {
        return safeApiCall { api.getPhoto() }
    }

    suspend fun getPhotoDetailById(id: String): Resource<PhotoResponse> {
        return safeApiCall { api.getPhotoDetailById(id) }
    }

    suspend fun getUserByUsername(userName: String): Resource<List<PhotoResponse>> {
        return safeApiCall { api.getUserByUsername(userName) }
    }


}