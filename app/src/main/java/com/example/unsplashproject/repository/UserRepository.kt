package com.example.unsplashproject.repository

import android.app.Application
import android.graphics.Bitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.LiveData
import com.example.unsplashproject.dao.UserDao
import com.example.unsplashproject.db.AppDatabase
import com.example.unsplashproject.model.login.UserModel

class UserRepository(application: Application) {

    private var userDao: UserDao
    private var getlist: LiveData<List<UserModel>>

    init {
        val database = AppDatabase.getInstance(context = application)
        userDao = database.userDao()
        getlist = userDao.getAllUserDetail()
    }

    suspend fun insertUser(userModel: UserModel) {
        userDao.insert(userModel)
    }

    suspend fun updateUser(userModel: UserModel) {
        userDao.update(userModel)
    }

    fun getUserInfo(): LiveData<List<UserModel>> {
        return getlist
    }

    fun isUserExist(email: String): LiveData<Boolean> {
        return userDao.isUserExist(email)

    }

    fun getUser(email: String): LiveData<UserModel?> {
        return userDao.getUser(email)
    }


    suspend fun updateUserFirstName(userModel: UserModel) {
        userDao.updateFirstName(userModel.firstName.toString(), userModel.id)
    }

    suspend fun updateUserLastName(userModel: UserModel) {
        userDao.updateLastName(userModel.lastName.toString(), userModel.id)
    }

    suspend fun updateUserEmail(userModel: UserModel) {
        userDao.updateEmail(userModel.email.toString(), userModel.id)
    }

    suspend fun updateUserPassword(userModel: UserModel) {
        userDao.updatePassword(userModel.password.toString(), userModel.id)
    }

    suspend fun updateUserImage(userModel: UserModel) {
        userDao.updateImage(userModel.image.toString(), userModel.id)
    }
}

