package com.example.unsplashproject.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.unsplashproject.dao.LoginDao
import com.example.unsplashproject.db.AppDatabase
import com.example.unsplashproject.model.login.LoginModel

class LoginRepository(application: Application) {

    private var loginDao: LoginDao
    private var getlist: LiveData<List<LoginModel>>

    init {
        val database = AppDatabase.getInstance(context = application)
        loginDao = database.loginDao()
        getlist = loginDao.getAllLoginDetail()
    }

    suspend fun insertLogin(loginModel: LoginModel) {
        loginDao.insert(loginModel)
    }

    suspend fun deleteLogin(loginModel: LoginModel) {
        loginDao.delete(loginModel)
    }

    suspend fun updateLogin(loginModel: LoginModel) {
        loginDao.update(loginModel)
    }

    fun getLoginInfo(): LiveData<List<LoginModel>> {
        return getlist
    }

}
