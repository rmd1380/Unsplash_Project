package com.example.unsplashproject.model.response

import com.example.unsplashproject.model.sitephotomodel.*
import com.google.gson.annotations.SerializedName


data class PhotoResponse(

    @SerializedName("id") var id: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("urls") var urls: Urls? = Urls(),
    @SerializedName("user") var user: User? = User(),
    @SerializedName("exif") var exif: Exif? = Exif(),
    @SerializedName("views") var views: Int? = null,
    @SerializedName("downloads") var downloads: Int? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("bio") var bio: String? = null,
    @SerializedName("profile_image") var profileImage: ProfileImage? = ProfileImage(),
)