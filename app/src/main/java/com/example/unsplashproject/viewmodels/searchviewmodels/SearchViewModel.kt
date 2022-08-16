package com.example.unsplashproject.viewmodels.searchviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashproject.model.response.SearchResponse
import com.example.unsplashproject.model.sitesearchmodel.Results
import com.example.unsplashproject.repositories.RepositorySearch
import com.example.unsplashproject.services.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repositoryFeed: RepositorySearch) :
    ViewModel() {

    var mQuery: MutableLiveData<String> = MutableLiveData()
    private var mPhotoSearch = MutableLiveData<Resource<SearchResponse>>()
    private var mUserSearch = MutableLiveData<Resource<SearchResponse>>()

    fun getLiveDataObserverPhotoSearch(query: String): LiveData<Resource<SearchResponse>> {
        viewModelScope.launch {
            mPhotoSearch.postValue(Resource.Loading())
            mPhotoSearch.postValue(repositoryFeed.getPhotosBySearch(query))
        }
        return mPhotoSearch
    }

    fun getLiveDataObserverUserSearch(query: String): LiveData<Resource<SearchResponse>> {
        viewModelScope.launch {
            mUserSearch.postValue(Resource.Loading())

            mUserSearch.postValue(repositoryFeed.getUsersBySearch(query))
        }
        return mUserSearch
    }

}