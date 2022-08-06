package com.example.unsplashproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.model.sitephotomodel.Photos
import com.example.unsplashproject.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var liveDataPhotoList: MutableLiveData<List<PhotoResponse>> = MutableLiveData()


    fun getLiveDataObserver():MutableLiveData<List<PhotoResponse>>
    {
        return liveDataPhotoList
    }
    fun loadListOfData()
    {
        viewModelScope.launch {
            repository.getPhoto(liveDataPhotoList)
        }

    }
}