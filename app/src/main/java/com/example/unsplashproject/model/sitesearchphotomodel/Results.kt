package com.example.unsplashproject.model.sitesearchphotomodel

import com.google.gson.annotations.SerializedName


data class Results(

    @SerializedName("id") var id: String? = null,
    @SerializedName("urls") var urls: Urls? = Urls(),
)