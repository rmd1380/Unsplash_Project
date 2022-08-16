package com.example.unsplashproject.model.login

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "loginInfo")
data class UserModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var email: String? = null,
    var password: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var image: String?=null
)