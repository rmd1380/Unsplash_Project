package com.example.unsplashproject.model.response

import com.example.unsplashproject.model.sitesearchmodel.Results
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total"       ) var total      : Int?               = null,
    @SerializedName("total_pages" ) var totalPages : Int?               = null,
    @SerializedName("results"     ) var results    : List<Results> = arrayListOf()
)