package com.example.unsplashproject.model.sitephotomodel
import com.google.gson.annotations.SerializedName


data class Location (

  @SerializedName("title"    ) var title    : String?   = null,
  @SerializedName("name"     ) var name     : String?   = null,
  @SerializedName("city"     ) var city     : String?   = null,
  @SerializedName("country"  ) var country  : String?   = null,
  @SerializedName("position" ) var position : Position? = Position()

)