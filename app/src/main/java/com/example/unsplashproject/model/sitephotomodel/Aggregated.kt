package com.example.unsplashproject.model.sitephotomodel

import com.google.gson.annotations.SerializedName


data class Aggregated (

  @SerializedName("type"  ) var type  : String? = null,
  @SerializedName("title" ) var title : String? = null

)