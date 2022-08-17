package com.example.unsplashproject.viewmodels.feedfragmentviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.repositories.RepositoryFeed
import com.example.unsplashproject.services.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedProfileFragmentViewModel @Inject constructor(private val repositoryFeed: RepositoryFeed) :
    ViewModel() {

    private var mUserPhotoList = MutableLiveData<Resource<List<PhotoResponse>>>()
    private var mUserByUsername = MutableLiveData<Resource<PhotoResponse>>()

    fun getLiveDataObserverUserPhotoList(username: String): LiveData<Resource<List<PhotoResponse>>> {
        viewModelScope.launch {
            mUserPhotoList.postValue(Resource.Loading())

            mUserPhotoList.postValue(repositoryFeed.getUserPhotos(username))
        }
        return mUserPhotoList
    }


    fun getLiveDataObserverUserByUsername(username: String): LiveData<Resource<PhotoResponse>> {
        viewModelScope.launch {
            mUserByUsername.postValue(Resource.Loading())

            mUserByUsername.postValue(repositoryFeed.getUserByUsername(username))
        }
        return mUserByUsername
    }

}
