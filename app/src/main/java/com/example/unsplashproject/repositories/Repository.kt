package com.example.unsplashproject.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.model.response.TopicResponse
import com.example.unsplashproject.services.ServiceApi
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@ActivityScoped
class Repository @Inject constructor(private val api: ServiceApi) {

    private val liveDataPhotoList: MutableLiveData<List<PhotoResponse>?> = MutableLiveData()
    private val liveDataPhoto: MutableLiveData<PhotoResponse?> = MutableLiveData()
    private val liveDataPhotoUserList: MutableLiveData<List<PhotoResponse>?> = MutableLiveData()
    private val liveDataTopicList: MutableLiveData<List<TopicResponse>?> = MutableLiveData()
    private val liveDataTopic: MutableLiveData<TopicResponse?> = MutableLiveData()
    private val liveDataTopicPhotos: MutableLiveData<List<PhotoResponse>?> = MutableLiveData()

    fun getPhoto(): MutableLiveData<List<PhotoResponse>?> {
        val call: Call<List<PhotoResponse>> = api.getPhoto()
        call.enqueue(object : Callback<List<PhotoResponse>> {
            override fun onResponse(
                call: Call<List<PhotoResponse>>,
                response: Response<List<PhotoResponse>>
            ) {
                liveDataPhotoList.postValue(response.body())
                Log.d("onResponse",response.code().toString())
            }

            override fun onFailure(call: Call<List<PhotoResponse>>, t: Throwable) {
                liveDataPhotoList.postValue(null)
            }
        })
        return liveDataPhotoList
    }

    fun getPhotoDetailById(id: String): MutableLiveData<PhotoResponse?> {
        val call: Call<PhotoResponse> = api.getPhotoDetailById(id)
        call.enqueue(object : Callback<PhotoResponse> {
            override fun onResponse(call: Call<PhotoResponse>, response: Response<PhotoResponse>) {
                liveDataPhoto.postValue(response.body())
                println("bodyyyy ${response.body().toString()}")
                Log.d("onResponse", response.code().toString())
            }

            override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
                liveDataPhoto.postValue(null)
            }
        })
        return liveDataPhoto
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

    fun getTopic(): MutableLiveData<List<TopicResponse>?> {
        val call: Call<List<TopicResponse>> = api.getTopic()
        call.enqueue(object : Callback<List<TopicResponse>> {
            override fun onResponse(
                call: Call<List<TopicResponse>>,
                response: Response<List<TopicResponse>>
            ) {
                liveDataTopicList.postValue(response.body())
                Log.d("TopiconResponse",response.code().toString())
            }
            override fun onFailure(call: Call<List<TopicResponse>>, t: Throwable) {
                liveDataTopicList.postValue(null)
                Log.d("TopiconFailure",t.message.toString())
            }
        })
        return liveDataTopicList
    }

    fun getTopicDetailById(id: String): MutableLiveData<TopicResponse?> {
        val call: Call<TopicResponse> = api.getTopicDetailById(id)
        call.enqueue(object : Callback<TopicResponse> {
            override fun onResponse(
                call: Call<TopicResponse>,
                response: Response<TopicResponse>
            ) {
                liveDataTopic.postValue(response.body())
            }

            override fun onFailure(call: Call<TopicResponse>, t: Throwable) {
                liveDataTopic.postValue(null)
            }

        })
        return liveDataTopic
    }

    fun getTopicPhotosById(id: String): MutableLiveData<List<PhotoResponse>?> {
        val call: Call<List<PhotoResponse>> = api.getTopicPhotosById(id)
        call.enqueue(object : Callback<List<PhotoResponse>> {
            override fun onResponse(
                call: Call<List<PhotoResponse>>,
                response: Response<List<PhotoResponse>>
            ) {
                liveDataTopicPhotos.postValue(response.body())
            }

            override fun onFailure(call: Call<List<PhotoResponse>>, t: Throwable) {
                liveDataTopicPhotos.postValue(null)
            }

        })
        return liveDataTopicPhotos
    }

}