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
class FeedDetailFragmentViewModel @Inject constructor(private val repositoryFeedDetail: RepositoryFeed) :
    ViewModel() {

    private var mPhotoData = MutableLiveData<Resource<PhotoResponse>>()

    fun getLiveDataObserverPhotoData(id: String):LiveData<Resource<PhotoResponse>> {
        viewModelScope.launch {
            mPhotoData.postValue(Resource.Loading())
            mPhotoData.postValue(repositoryFeedDetail.getPhotoDetailById(id))
        }
        return mPhotoData
    }
}

