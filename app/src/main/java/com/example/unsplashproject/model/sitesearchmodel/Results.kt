package com.example.unsplashproject.model.sitesearchmodel

import com.google.gson.annotations.SerializedName


data class Results(

    @SerializedName("id") var id: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("first_name") var firstName: String? = null,
    @SerializedName("last_name") var lastName: String? = null,
    @SerializedName("twitter_username") var twitterUsername: String? = null,
    @SerializedName("portfolio_url") var portfolioUrl: String? = null,
    @SerializedName("bio") var bio: String? = null,
    @SerializedName("location") var location: String? = null,
    @SerializedName("links") var links: Links? = Links(),
    @SerializedName("profile_image") var profileImage: ProfileImage? = ProfileImage(),
    @SerializedName("instagram_username") var instagramUsername: String? = null,
    @SerializedName("total_collections") var totalCollections: Int? = null,
    @SerializedName("total_likes") var totalLikes: Int? = null,
    @SerializedName("total_photos") var totalPhotos: Int? = null,
    @SerializedName("accepted_tos") var acceptedTos: Boolean? = null,
    @SerializedName("for_hire") var forHire: Boolean? = null,
    @SerializedName("social") var social: Social? = Social(),
    @SerializedName("followed_by_user") var followedByUser: Boolean? = null,
    @SerializedName("photos") var photos: Photos? = Photos(),

    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("promoted_at") var promotedAt: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("color") var color: String? = null,
    @SerializedName("blur_hash") var blurHash: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("alt_description") var altDescription: String? = null,
    @SerializedName("urls") var urls: Urls? = Urls(),
    @SerializedName("categories") var categories: ArrayList<String> = arrayListOf(),
    @SerializedName("likes") var likes: Int? = null,
    @SerializedName("liked_by_user") var likedByUser: Boolean? = null,
    @SerializedName("current_user_collections") var currentUserCollections: ArrayList<String> = arrayListOf(),
    @SerializedName("sponsorship") var sponsorship: String? = null,
    @SerializedName("user") var user: User? = User(),
)