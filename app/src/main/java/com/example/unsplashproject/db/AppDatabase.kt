package com.example.unsplashproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.unsplashproject.dao.LoginDao
import com.example.unsplashproject.model.login.LoginModel

@Database(entities = [LoginModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDao

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "LoginDatabase"
                ).fallbackToDestructiveMigration().build()
            }
            return instance as AppDatabase
        }

    }

}