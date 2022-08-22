package com.example.unsplashproject.viewmodels.topicfragmentviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.unsplashproject.model.response.TopicResponse
import com.example.unsplashproject.paging.BasePagingSource
import com.example.unsplashproject.repositories.RepositoryTopic
import com.example.unsplashproject.services.Resource
import com.example.unsplashproject.services.ServiceApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicDetailFragmentViewModel @Inject constructor(
    private val repositoryFeed: RepositoryTopic,
    private val api: ServiceApi
) :
    ViewModel() {

    private var mTopicDetail = MutableLiveData<Resource<TopicResponse>>()

    fun getLiveDataObserverTopicDetail(id: String): LiveData<Resource<TopicResponse>> {
        viewModelScope.launch {
            mTopicDetail.postValue(Resource.Loading())

            mTopicDetail.postValue(repositoryFeed.getTopicDetailById(id))
        }
        return mTopicDetail
    }

    fun getTopicImages(id: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                BasePagingSource {
                    api.getTopicPhotosById(id, it, 10)
                }
            }
        ).liveData.cachedIn(viewModelScope)

}