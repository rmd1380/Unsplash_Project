package com.example.unsplashproject.model.sitesearchusermodel

import com.google.gson.annotations.SerializedName


data class Results (

  @SerializedName("id"                 ) var id                : String?           = null,
  @SerializedName("username"           ) var username          : String?           = null,
  @SerializedName("name"               ) var name              : String?           = null,
  @SerializedName("profile_image"      ) var profileImage      : ProfileImage?     = ProfileImage(),

)