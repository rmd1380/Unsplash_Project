package com.example.unsplashproject.services

import com.example.unsplashproject.model.response.PhotoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PhotoService {
    @Headers("Authorization: Client-ID xvJiC8gqtS0HJuJrDjQpDe5cWghraEfPnu3Tqh5O81M")
    @GET("photos")
    fun getPhoto(): Call<List<PhotoResponse>>

    @Headers("Authorization: Client-ID xvJiC8gqtS0HJuJrDjQpDe5cWghraEfPnu3Tqh5O81M")
    @GET("photos/{id}")
    fun getPhotoDetailById(@Path("id")id:String): Call<PhotoResponse>

    @Headers("Authorization: Client-ID xvJiC8gqtS0HJuJrDjQpDe5cWghraEfPnu3Tqh5O81M")
    @GET("users/{username}/photos")
    fun getUserByUsername(@Path("username")username:String): Call<List<PhotoResponse>>

}