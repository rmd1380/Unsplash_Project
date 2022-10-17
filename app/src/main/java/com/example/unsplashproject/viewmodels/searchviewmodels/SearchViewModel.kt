package com.example.unsplashproject.viewmodels.searchviewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.unsplashproject.paging.SearchPhotoPagingSource
import com.example.unsplashproject.paging.SearchUserPagingSource
import com.example.unsplashproject.services.ServiceApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val api: ServiceApi) :
    ViewModel() {

    var mQuery: MutableLiveData<String> = MutableLiveData()

    fun getPhotoSearchImage(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { SearchPhotoPagingSource(api, query) }
        ).liveData.cachedIn(viewModelScope)

//    fun getPhotoSearchImage1(query: String) =
//        createPager {
//            val response=api.getPhotosBySearch(query,it,10)
//        }

    fun getUserSearchImage(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { SearchUserPagingSource(api, query) }
        ).liveData.cachedIn(viewModelScope)


}