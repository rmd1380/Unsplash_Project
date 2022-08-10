package com.example.unsplashproject.viewmodels.topicfragmentviewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unsplashproject.model.response.TopicResponse
import com.example.unsplashproject.repositories.RepositoryFeed
import com.example.unsplashproject.repositories.RepositoryTopic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopicFragmentViewModel @Inject constructor( private val repositoryFeed: RepositoryTopic) : ViewModel() {

    private var liveDataTopicList: MutableLiveData<List<TopicResponse>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<TopicResponse>?>
    {
        return repositoryFeed.getTopic()
    }

}