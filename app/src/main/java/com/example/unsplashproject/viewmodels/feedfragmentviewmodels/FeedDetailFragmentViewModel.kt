package com.example.unsplashproject.viewmodels.feedfragmentviewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedDetailFragmentViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    private var liveDataPhotoUserList: MutableLiveData<PhotoResponse?> = MutableLiveData()

    fun getLiveDataObserver(id: String): MutableLiveData<PhotoResponse?> {
        return repository.getPhotoDetailById(id)
    }
}