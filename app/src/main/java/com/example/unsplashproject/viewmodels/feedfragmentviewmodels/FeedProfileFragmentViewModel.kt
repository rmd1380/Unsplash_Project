package com.example.unsplashproject.viewmodels.feedfragmentviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.unsplashproject.model.response.FeedPhotoResponse
import com.example.unsplashproject.paging.BasePagingSource
import com.example.unsplashproject.repositories.RepositoryFeed
import com.example.unsplashproject.services.Resource
import com.example.unsplashproject.services.ServiceApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedProfileFragmentViewModel @Inject constructor(
    private val repositoryFeed: RepositoryFeed,
    private val api: ServiceApi
) :
    ViewModel() {

    private var mUserByUsername = MutableLiveData<Resource<FeedPhotoResponse>>()

    fun getUserImages(username: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { BasePagingSource{
                api.getUserPhotos(username,it,10)
            } }
        ).liveData.cachedIn(viewModelScope)


    fun getLiveDataObserverUserByUsername(username: String): LiveData<Resource<FeedPhotoResponse>> {
        viewModelScope.launch {
            mUserByUsername.postValue(Resource.Loading())

            mUserByUsername.postValue(repositoryFeed.getUserByUsername(username))
        }
        return mUserByUsername
    }

}
