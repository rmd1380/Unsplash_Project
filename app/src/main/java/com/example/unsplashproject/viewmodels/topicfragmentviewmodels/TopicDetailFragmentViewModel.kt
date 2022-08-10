package com.example.unsplashproject.viewmodels.topicfragmentviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.model.response.TopicResponse
import com.example.unsplashproject.repositories.RepositoryTopic
import com.example.unsplashproject.services.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicDetailFragmentViewModel @Inject constructor(private val repositoryFeed: RepositoryTopic) :
    ViewModel() {

    private var mTopicDetail = MutableLiveData<Resource<TopicResponse>>()
    private var mTopicPhoto = MutableLiveData<Resource<List<PhotoResponse>>>()
    
    fun getLiveDataObserverTopicDetail(id: String): LiveData<Resource<TopicResponse>> {
        viewModelScope.launch {
            mTopicDetail.postValue(Resource.Loading())

            mTopicDetail.postValue(repositoryFeed.getTopicDetailById(id))
        }
        return mTopicDetail
    }

    fun getLiveDataObserverTopicPhoto(id: String): LiveData<Resource<List<PhotoResponse>>> {

        viewModelScope.launch {
            mTopicPhoto.postValue(Resource.Loading())

            mTopicPhoto.postValue(repositoryFeed.getTopicPhotosById(id))
        }
        return mTopicPhoto

    }

}