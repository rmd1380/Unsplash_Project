package com.example.unsplashproject.repositories

import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.model.response.TopicResponse
import com.example.unsplashproject.services.Resource
import com.example.unsplashproject.services.ServiceApi
import javax.inject.Inject

class RepositoryTopic @Inject constructor(private val api: ServiceApi) : BaseRepository() {

    suspend fun getTopic(): Resource<List<TopicResponse>> {
        return safeApiCall { api.getTopic() }
    }

    suspend fun getTopicDetailById(id: String): Resource<TopicResponse> {
        return safeApiCall { api.getTopicDetailById(id) }
    }

    suspend fun getTopicPhotosById(id: String): Resource<List<PhotoResponse>> {
        return safeApiCall { api.getTopicPhotosById(id) }
    }
}