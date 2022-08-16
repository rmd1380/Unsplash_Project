package com.example.unsplashproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.unsplashproject.model.login.UserModel
import com.example.unsplashproject.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {


    private var repository: UserRepository = UserRepository(application)
    private var allUserInfoList = repository.getUserInfo()

    fun insert(userModel: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(userModel)
        }
    }

    fun update(userModel: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(userModel)
        }
    }

    fun getAllUserInfo(): LiveData<List<UserModel>> {
        return allUserInfoList
    }

    fun isUserExist(email: String): LiveData<Boolean> {
        return repository.isUserExist(email)
    }

    fun getUser(email: String): LiveData<UserModel?> {
        return repository.getUser(email)
    }


    fun updateFirstName(userModel: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserFirstName(userModel)
        }
    }

    fun updateLastName(userModel: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserLastName(userModel)
        }

    }

    fun updateEmail(userModel: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserEmail(userModel)
        }
    }

    fun updatePassword(userModel: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserPassword(userModel)
        }
    }

    fun updateUserImage(userModel: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserImage(userModel)
        }
    }
}
