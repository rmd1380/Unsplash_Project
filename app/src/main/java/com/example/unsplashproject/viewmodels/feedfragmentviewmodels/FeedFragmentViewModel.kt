package com.example.unsplashproject.viewmodels.feedfragmentviewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.unsplashproject.paging.BasePagingSource
import com.example.unsplashproject.services.ServiceApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedFragmentViewModel @Inject constructor(
    private val api: ServiceApi,
) :
    ViewModel() {


    fun getImages() =
        Pager(config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { BasePagingSource{
                api.getPhoto(it,10)
            } }
        ).liveData.cachedIn(viewModelScope)
}

