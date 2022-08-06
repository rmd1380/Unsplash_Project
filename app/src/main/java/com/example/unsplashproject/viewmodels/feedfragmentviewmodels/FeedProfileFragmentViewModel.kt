package com.example.unsplashproject.viewmodels.feedfragmentviewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.repositories.Repository
import com.example.unsplashproject.services.ServiceApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FeedProfileFragmentViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    private var liveDataPhotoList: MutableLiveData<List<PhotoResponse>?> = MutableLiveData()

    fun getLiveDataObserver(id: String): MutableLiveData<PhotoResponse?> {
        return repository.getPhotoDetailById(id)
    }

    fun loadListOfData(userName: String): MutableLiveData<List<PhotoResponse>?> {
        return repository.getUserByUsername(userName)
    }

}
