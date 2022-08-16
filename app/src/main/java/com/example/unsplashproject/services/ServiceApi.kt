package com.example.unsplashproject.services

import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.model.response.SearchResponse
import com.example.unsplashproject.model.response.TopicResponse
import com.example.unsplashproject.model.sitesearchmodel.Results
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    @GET("photos")
    suspend fun getPhoto(): Response<List<PhotoResponse>>

    @GET("/photos/{id}")
    suspend fun getPhotoDetailById(@Path("id") id: String): Response<PhotoResponse>

    @GET("users/{username}/photos")
    suspend fun getUserByUsername(@Path("username") username: String): Response<List<PhotoResponse>>

    @GET("topics")
    suspend fun getTopic(): Response<List<TopicResponse>>

    @GET("topics/{id}")
    suspend fun getTopicDetailById(@Path("id") id: String): Response<TopicResponse>

    @GET("topics/{id}/photos")
    suspend fun getTopicPhotosById(@Path("id") id: String): Response<List<PhotoResponse>>

    @GET("search/photos")
    fun getPhotosBySearch(@Query("query") query: String): Response<SearchResponse>

    @GET("search/users")
    fun getUsersBySearch(@Query("query") query: String): Response<SearchResponse>

}