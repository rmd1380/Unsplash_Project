package com.example.unsplashproject.model.login

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginModel(
    @PrimaryKey val email: String="",
    val password: String
)