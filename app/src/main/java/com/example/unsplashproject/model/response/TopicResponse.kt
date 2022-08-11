package com.example.unsplashproject.model.response

import com.example.unsplashproject.model.sitetopicmodel.*
import com.google.gson.annotations.SerializedName

data class TopicResponse(
    @SerializedName("id") var id: String? = null,
    @SerializedName("slug") var slug: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("published_at") var publishedAt: String? = null,
    @SerializedName("links") var links: Links? = Links(),
    @SerializedName("cover_photo") var coverPhoto: CoverPhoto? = CoverPhoto(),
    @SerializedName("preview_photos") var previewPhotos: List<PreviewPhotos> = arrayListOf()

)