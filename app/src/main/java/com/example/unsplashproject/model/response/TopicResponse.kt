package com.example.unsplashproject.model.response

import com.example.unsplashproject.model.sitetopicmodel.*
import com.google.gson.annotations.SerializedName

data class TopicResponse(
    @SerializedName("id") var id: String? = null,
    @SerializedName("slug") var slug: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("published_at") var publishedAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("starts_at") var startsAt: String? = null,
    @SerializedName("ends_at") var endsAt: String? = null,
    @SerializedName("only_submissions_after") var onlySubmissionsAfter: String? = null,
    @SerializedName("visibility") var visibility: String? = null,
    @SerializedName("featured") var featured: Boolean? = null,
    @SerializedName("total_photos") var totalPhotos: Int? = null,
    @SerializedName("current_user_contributions") var currentUserContributions: ArrayList<String> = arrayListOf(),
    @SerializedName("total_current_user_submissions") var totalCurrentUserSubmissions: String? = null,
    @SerializedName("links") var links: Links? = Links(),
    @SerializedName("status") var status: String? = null,
    @SerializedName("owners") var owners: ArrayList<Owners> = arrayListOf(),
    @SerializedName("cover_photo") var coverPhoto: CoverPhoto? = CoverPhoto(),
    @SerializedName("preview_photos") var previewPhotos: ArrayList<PreviewPhotos> = arrayListOf(),

)