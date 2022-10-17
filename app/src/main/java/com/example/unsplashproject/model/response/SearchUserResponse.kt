package com.example.unsplashproject.model.response

import com.example.unsplashproject.model.sitesearchusermodel.Results
import com.google.gson.annotations.SerializedName

data class SearchUserResponse(

    @SerializedName("total") var total: Int? = null,
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()
)


