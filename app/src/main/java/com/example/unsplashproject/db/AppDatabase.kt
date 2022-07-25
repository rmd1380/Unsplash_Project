package com.example.unsplashproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.unsplashproject.dao.LoginDao
import com.example.unsplashproject.model.LoginModel

@Database(entities = [LoginModel::class], version = 1)
abstract class LoginDataBase : RoomDatabase() {

    abstract fun loginDao(): LoginDao

    companion object {
        private var instance: LoginDataBase? = null

    }

    fun getInstance(context: Context): LoginDataBase? {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context,
                LoginDataBase::class.java, "LoginDatabase"
            ).fallbackToDestructiveMigration().build()
        }
        return instance
    }
}