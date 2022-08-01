package com.example.unsplashproject.model.sitephotomodel
import com.google.gson.annotations.SerializedName


data class Health (

  @SerializedName("status"      ) var status     : String? = null,
  @SerializedName("approved_on" ) var approvedOn : String? = null

)