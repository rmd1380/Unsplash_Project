package com.example.unsplashproject.viewmodels.searchviewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.model.response.SearchResponse
import com.example.unsplashproject.model.sitesearchmodel.Results
import com.example.unsplashproject.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoSearchViewModel @Inject constructor(private val repository: Repository):ViewModel() {

    val liveDataPhotoSearch:MutableLiveData<List<SearchResponse>> = MutableLiveData()

    var mQuery: MutableLiveData<String> = MutableLiveData()

    fun getLiveDataObserver(query: String): MutableLiveData<SearchResponse?> {
        return repository.getPhotosBySearch(query)
    }
}