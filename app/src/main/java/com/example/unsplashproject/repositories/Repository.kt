package com.example.unsplashproject.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.services.ServiceApi
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@ActivityScoped
class Repository @Inject constructor(private val api: ServiceApi) {

    private val liveDataPhotoList: MutableLiveData<PhotoResponse?> = MutableLiveData()
    private val liveDataPhotoUserList: MutableLiveData<List<PhotoResponse>?> = MutableLiveData()

    fun getPhoto(liveDataPhotoList: MutableLiveData<List<PhotoResponse>>) {
        val call: Call<List<PhotoResponse>> = api.getPhoto()
        call.enqueue(object : Callback<List<PhotoResponse>> {
            override fun onResponse(
                call: Call<List<PhotoResponse>>,
                response: Response<List<PhotoResponse>>
            ) {
                liveDataPhotoList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<PhotoResponse>>, t: Throwable) {
                liveDataPhotoList.postValue(null)
            }
        })
    }

    fun getPhotoDetailById(id: String): MutableLiveData<PhotoResponse?> {
        val call: Call<PhotoResponse> = api.getPhotoDetailById(id)
        call.enqueue(object : Callback<PhotoResponse> {
            override fun onResponse(call: Call<PhotoResponse>, response: Response<PhotoResponse>) {
                liveDataPhotoList.postValue(response.body())
                println("bodyyyy ${response.body().toString()}")
                Log.d("onResponse", response.code().toString())
            }

            override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
                liveDataPhotoList.postValue(null)
            }
        })
        return liveDataPhotoList
    }

    fun getUserByUsername(userName: String): MutableLiveData<List<PhotoResponse>?> {
        val call: Call<List<PhotoResponse>> = api.getUserByUsername(userName)
        call.enqueue(object : Callback<List<PhotoResponse>> {
            override fun onResponse(
                call: Call<List<PhotoResponse>>,
                response: Response<List<PhotoResponse>>
            ) {
                liveDataPhotoUserList.postValue(response.body())
                Log.d("onResponse", response.code().toString())
            }

            override fun onFailure(call: Call<List<PhotoResponse>>, t: Throwable) {
                liveDataPhotoUserList.postValue(null)
            }
        })
        return liveDataPhotoUserList
    }

}