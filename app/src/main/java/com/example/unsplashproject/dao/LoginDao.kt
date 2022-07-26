package com.example.unsplashproject.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.unsplashproject.model.LoginModel

@Dao
interface LoginDao {
    //CRUD
    @Insert
    fun insert(login: LoginModel)

    @Update
    fun update(login: LoginModel)

    @Delete
    fun delete(login: LoginModel)

    @Query("SELECT * FROM LoginModel")
    fun getAllLoginDetail(): LiveData<List<LoginModel>>
}