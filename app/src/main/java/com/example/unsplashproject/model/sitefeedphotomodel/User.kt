package com.example.unsplashproject.model.sitefeedphotomodel

import com.google.gson.annotations.SerializedName


data class User (

  @SerializedName("id"                 ) var id                : String?       = null,
  @SerializedName("updated_at"         ) var updatedAt         : String?       = null,
  @SerializedName("username"           ) var username          : String?       = null,
  @SerializedName("name"               ) var name              : String?       = null,
  @SerializedName("first_name"         ) var firstName         : String?       = null,
  @SerializedName("last_name"          ) var lastName          : String?       = null,
  @SerializedName("bio"                ) var bio               : String?       = null,
  @SerializedName("profile_image"      ) var profileImage      : ProfileImage? = ProfileImage(),

)