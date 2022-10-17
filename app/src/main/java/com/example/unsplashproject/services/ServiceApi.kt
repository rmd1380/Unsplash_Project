package com.example.unsplashproject.services

import com.example.unsplashproject.model.response.*
import com.example.unsplashproject.model.sitesearchphotomodel.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    @GET("photos")
    suspend fun getPhoto(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<List<FeedPhotoResponse>>

    @GET("/photos/{id}")
    suspend fun getPhotoDetailById(@Path("id") id: String): Response<FeedPhotoResponse>

    @GET("users/{username}/photos")
    suspend fun getUserPhotos(
        @Path("username") username: String?,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<List<FeedPhotoResponse>>

    @GET("users/{username}")
    suspend fun getUserByUsername(@Path("username") username: String): Response<FeedPhotoResponse>

    @GET("topics")
    suspend fun getTopic(@Query("per_page") per_page: Int): Response<List<TopicResponse>>

    @GET("topics/{id}")
    suspend fun getTopicDetailById(@Path("id") id: String): Response<TopicResponse>

    @GET("topics/{id}/photos")
    suspend fun getTopicPhotosById(
        @Path("id") id: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<List<FeedPhotoResponse>>

    @GET("search/photos")
    suspend fun getPhotosBySearch(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<SearchPhotoResponse>

    @GET("search/users")
    suspend fun getUsersBySearch(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<SearchUserResponse>

}