package com.example.unsplashproject.model.sitetopicmodel


import com.google.gson.annotations.SerializedName


data class CoverPhoto(

    @SerializedName("id") var id: String? = null,
    @SerializedName("urls") var urls: Urls? = Urls(),
)