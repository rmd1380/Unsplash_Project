package com.example.unsplashproject.model.response

import com.example.unsplashproject.model.sitetopicmodel.*
import com.google.gson.annotations.SerializedName

data class TopicResponse(
    @SerializedName("id") var id: String? = null,
    @SerializedName("slug") var slug: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("cover_photo") var coverPhoto: CoverPhoto? = CoverPhoto(),
)