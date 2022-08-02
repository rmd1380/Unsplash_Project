package com.example.unsplashproject.model.sitetopicmodel

import com.google.gson.annotations.SerializedName


data class Urls (

  @SerializedName("raw"      ) var raw     : String? = null,
  @SerializedName("full"     ) var full    : String? = null,
  @SerializedName("regular"  ) var regular : String? = null,
  @SerializedName("small"    ) var small   : String? = null,
  @SerializedName("thumb"    ) var thumb   : String? = null,
  @SerializedName("small_s3" ) var smallS3 : String? = null

)