package com.example.unsplashproject.viewmodels.topicfragmentviewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.model.response.TopicResponse
import com.example.unsplashproject.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicDetailFragmentViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    private val liveDataTopicList: MutableLiveData<List<TopicResponse>?> = MutableLiveData()

    fun getLiveDataObserver(id: String): MutableLiveData<TopicResponse?> {
        return repository.getTopicDetailById(id)
    }

    fun loadListOfData(id: String): MutableLiveData<List<PhotoResponse>?> {

        return repository.getTopicPhotosById(id)

    }
}