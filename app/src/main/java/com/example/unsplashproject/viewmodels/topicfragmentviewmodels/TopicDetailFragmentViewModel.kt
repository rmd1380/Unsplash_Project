package com.example.unsplashproject.viewmodels.topicfragmentviewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.model.response.TopicResponse
import com.example.unsplashproject.repositories.RepositoryFeed
import com.example.unsplashproject.repositories.RepositoryTopic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopicDetailFragmentViewModel @Inject constructor(private val repositoryFeed: RepositoryTopic):ViewModel() {

    private val liveDataTopicList: MutableLiveData<List<TopicResponse>?> = MutableLiveData()

    fun getLiveDataObserver(id: String): MutableLiveData<TopicResponse?> {
        return repositoryFeed.getTopicDetailById(id)
    }

    fun loadListOfData(id: String): MutableLiveData<List<PhotoResponse>?> {

        return repositoryFeed.getTopicPhotosById(id)

    }
}