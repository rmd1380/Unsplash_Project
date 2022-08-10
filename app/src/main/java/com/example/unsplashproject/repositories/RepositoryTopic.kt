package com.example.unsplashproject.repositories

import android.icu.util.LocaleData
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

class RepositoryTopic @Inject constructor(private val api:ServiceApi) {

    private val liveDataTopicList: MutableLiveData<List<TopicResponse>?> = MutableLiveData()
    private val liveDataTopic: MutableLiveData<TopicResponse?> = MutableLiveData()
    private val liveDataTopicPhotos: MutableLiveData<List<PhotoResponse>?> = MutableLiveData()

    fun getTopic(): MutableLiveData<List<TopicResponse>?> {
        val call: Call<List<TopicResponse>> = api.getTopic()
        call.enqueue(object : Callback<List<TopicResponse>> {
            override fun onResponse(
                call: Call<List<TopicResponse>>,
                response: Response<List<TopicResponse>>
            ) {
                liveDataTopicList.postValue(response.body())
                Log.d("TopiconResponse", response.code().toString())
            }

            override fun onFailure(call: Call<List<TopicResponse>>, t: Throwable) {
                liveDataTopicList.postValue(null)
                Log.d("TopiconFailure", t.message.toString())
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