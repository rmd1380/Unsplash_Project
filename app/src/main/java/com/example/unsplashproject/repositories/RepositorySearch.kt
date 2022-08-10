package com.example.unsplashproject.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.unsplashproject.model.response.SearchResponse
import com.example.unsplashproject.services.ServiceApi
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RepositorySearch @Inject constructor(private val api:ServiceApi) {

    private val liveDataSearchPhotos: MutableLiveData<SearchResponse?> = MutableLiveData()
    private val liveDataSearchUsers: MutableLiveData<SearchResponse?> = MutableLiveData()

    fun getPhotosBySearch(query: String): MutableLiveData<SearchResponse?> {
        val call: Call<SearchResponse> = api.getPhotosBySearch(query)
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                liveDataSearchPhotos.postValue(response.body())
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                liveDataSearchPhotos.postValue(null)
                Log.d("onFailureSearch", t.message.toString())
            }
        })
        return liveDataSearchPhotos
    }

    fun getUsersBySearch(query: String): MutableLiveData<SearchResponse?> {
        val call: Call<SearchResponse> = api.getUsersBySearch(query)
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                liveDataSearchUsers.postValue(response.body())
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                liveDataSearchUsers.postValue(null)
                Log.d("onFailureSearch", t.message.toString())
            }
        })
        return liveDataSearchUsers
    }
}