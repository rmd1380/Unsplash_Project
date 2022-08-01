package com.example.unsplashproject.services

import com.example.unsplashproject.model.response.PhotoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface PhotoService {
    @Headers("Authorization: Client-ID xvJiC8gqtS0HJuJrDjQpDe5cWghraEfPnu3Tqh5O81M")
    @GET("photos")
    fun getPhoto(): Call<List<PhotoResponse>>
}