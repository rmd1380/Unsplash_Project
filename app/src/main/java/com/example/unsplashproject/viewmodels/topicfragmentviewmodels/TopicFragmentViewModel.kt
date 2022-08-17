package com.example.unsplashproject.viewmodels.topicfragmentviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashproject.model.response.TopicResponse
import com.example.unsplashproject.repositories.RepositoryTopic
import com.example.unsplashproject.services.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicFragmentViewModel @Inject constructor( private val repositoryFeed: RepositoryTopic) : ViewModel() {

    private var mTopicList = MutableLiveData<Resource<List<TopicResponse>>>()

    fun getLiveDataObserverTopicList(): LiveData<Resource<List<TopicResponse>>> {
        viewModelScope.launch {
            mTopicList.postValue(Resource.Loading())

            mTopicList.postValue(repositoryFeed.getTopic())
        }
        return mTopicList
    }

}