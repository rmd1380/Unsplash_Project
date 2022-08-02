package com.example.unsplashproject.model.sitetopicmodel

import com.google.gson.annotations.SerializedName


data class Currentevents (

  @SerializedName("status"      ) var status     : String? = null,
  @SerializedName("approved_on" ) var approvedOn : String? = null

)