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
class FeedFragmentViewModel @Inject constructor(private val repositoryFeed: RepositoryFeed) :
    ViewModel() {

    private var mPhotoList = MutableLiveData<Resource<List<PhotoResponse>>>()

    fun getLiveDataObserverPhotoList():LiveData<Resource<List<PhotoResponse>>> {
        viewModelScope.launch {
            mPhotoList.postValue(Resource.Loading())

            mPhotoList.postValue(repositoryFeed.getPhoto())
        }
        return mPhotoList
    }

}