package com.example.unsplashproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.unsplashproject.model.login.LoginModel
import com.example.unsplashproject.repository.LoginRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: LoginRepository = LoginRepository(application)
    private var allInfoList = repository.getLoginInfo()

    suspend fun insert(loginModel: LoginModel) {
        repository.insertLogin(loginModel)
    }

    suspend fun update(loginModel: LoginModel) {
        repository.updateLogin(loginModel)
    }

    suspend fun delete(loginModel: LoginModel) {
        repository.deleteLogin(loginModel)
    }

    fun getAllInfo(): LiveData<List<LoginModel>> {
        return allInfoList
    }


}