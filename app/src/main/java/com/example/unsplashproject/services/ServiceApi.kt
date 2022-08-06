package com.example.unsplashproject.services

import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.model.response.SearchResponse
import com.example.unsplashproject.model.response.TopicResponse
import com.example.unsplashproject.model.sitephotomodel.Photos
import com.example.unsplashproject.model.sitephotomodel.User
import com.example.unsplashproject.model.sitesearchmodel.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    @Headers("Authorization: Client-ID xvJiC8gqtS0HJuJrDjQpDe5cWghraEfPnu3Tqh5O81M")
    @GET("photos")
    fun getPhoto(): Call<List<PhotoResponse>>

    @Headers("Authorization: Client-ID xvJiC8gqtS0HJuJrDjQpDe5cWghraEfPnu3Tqh5O81M")
    @GET("/photos/{id}")
    fun getPhotoDetailById(@Path("id")id:String): Call<PhotoResponse>

    @Headers("Authorization: Client-ID xvJiC8gqtS0HJuJrDjQpDe5cWghraEfPnu3Tqh5O81M")
    @GET("users/{username}/photos")
    fun getUserByUsername(@Path("username")username:String): Call<List<PhotoResponse>>

    @Headers("Authorization: Client-ID xvJiC8gqtS0HJuJrDjQpDe5cWghraEfPnu3Tqh5O81M")
    @GET("topics")
    fun getTopic():Call<List<TopicResponse>>

    @Headers("Authorization: Client-ID xvJiC8gqtS0HJuJrDjQpDe5cWghraEfPnu3Tqh5O81M")
    @GET("topics/{id}")
    fun getTopicDetailById(@Path("id") id:String):Call<TopicResponse>

    @Headers("Authorization: Client-ID xvJiC8gqtS0HJuJrDjQpDe5cWghraEfPnu3Tqh5O81M")
    @GET("topics/{id}/photos")
    fun getTopicPhotosById(@Path("id") id:String):Call<List<PhotoResponse>>

    @Headers("Authorization: Client-ID xvJiC8gqtS0HJuJrDjQpDe5cWghraEfPnu3Tqh5O81M")
    @GET("search/photos")
    fun getPhotosBySearch(@Query("query")query:String):Call<SearchResponse>

    @Headers("Authorization: Client-ID xvJiC8gqtS0HJuJrDjQpDe5cWghraEfPnu3Tqh5O81M")
    @GET("search/users")
    fun getUsersBySearch(@Query("query")query:String):Call<SearchResponse>

}