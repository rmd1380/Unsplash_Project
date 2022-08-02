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
    @SerializedName("preview_photos") var previewPhotossss: List<PreviewPhotos>,

    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("promoted_at") var promotedAt: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("color") var color: String? = null,
    @SerializedName("blur_hash") var blurHash: String? = null,
    @SerializedName("alt_description") var altDescription: String? = null,
    @SerializedName("urls") var urls: Urls? = Urls(),
    @SerializedName("categories") var categories: ArrayList<String> = arrayListOf(),
    @SerializedName("likes") var likes: Int? = null,
    @SerializedName("liked_by_user") var likedByUser: Boolean? = null,
    @SerializedName("current_user_collections") var currentUserCollections: ArrayList<String> = arrayListOf(),
    @SerializedName("sponsorship") var sponsorship: String? = null,
    @SerializedName("topic_submissions") var topicSubmissions: TopicSubmissions? = TopicSubmissions(),
    @SerializedName("user") var user: User? = User()
)