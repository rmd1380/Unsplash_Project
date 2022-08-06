package com.example.unsplashproject.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedFragmentViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

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