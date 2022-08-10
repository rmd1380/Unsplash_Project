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

    private var mPhotoDetail = MutableLiveData<Resource<PhotoResponse>>()
    private var mUserPhotoList = MutableLiveData<Resource<List<PhotoResponse>>>()

    fun getLiveDataObserverPhotoDetail(id: String): LiveData<Resource<PhotoResponse>> {
        viewModelScope.launch {
            mPhotoDetail.postValue(Resource.Loading())

            mPhotoDetail.postValue(repositoryFeed.getPhotoDetailById(id))
        }
        return mPhotoDetail
    }

    fun getLiveDataObserverUserPhotoList(id: String): LiveData<Resource<List<PhotoResponse>>> {
        viewModelScope.launch {
            mUserPhotoList.postValue(Resource.Loading())

            mUserPhotoList.postValue(repositoryFeed.getUserByUsername(id))
        }
        return mUserPhotoList
    }

}
