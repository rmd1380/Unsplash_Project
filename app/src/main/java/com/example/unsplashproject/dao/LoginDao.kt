package com.example.unsplashproject.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.unsplashproject.model.LoginModel

interface LoginDao {
    //CRUD
    @Insert
    fun insert(login: LoginModel)

    @Update
    fun update(login: LoginModel)

    @Delete
    fun delete(login: LoginModel)
    @Query("SELECT * FROM LoginModel")
    fun getAllLoginDetail():LiveData<List<LoginModel>>
}