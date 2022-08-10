package com.example.unsplashproject.viewmodels.searchviewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unsplashproject.model.response.SearchResponse
import com.example.unsplashproject.repositories.RepositoryFeed
import com.example.unsplashproject.repositories.RepositorySearch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repositoryFeed: RepositorySearch):ViewModel() {

    var mQuery: MutableLiveData<String> = MutableLiveData()

    fun getLiveDataObserverPhoto(query: String): MutableLiveData<SearchResponse?> {
        return repositoryFeed.getPhotosBySearch(query)
    }

    fun getLiveDataObserverUser(query: String): MutableLiveData<SearchResponse?> {
        return repositoryFeed.getUsersBySearch(query)
    }

}