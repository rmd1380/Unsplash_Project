package com.example.unsplashproject.services

import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.model.response.SearchResponse
import com.example.unsplashproject.model.response.TopicResponse
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
    fun getTopic(): Call<List<TopicResponse>>

    @GET("topics/{id}")
    fun getTopicDetailById(@Path("id") id: String): Call<TopicResponse>

    @GET("topics/{id}/photos")
    fun getTopicPhotosById(@Path("id") id: String): Call<List<PhotoResponse>>

    @GET("search/photos")
    fun getPhotosBySearch(@Query("query") query: String): Call<SearchResponse>

    @GET("search/users")
    fun getUsersBySearch(@Query("query") query: String): Call<SearchResponse>

}