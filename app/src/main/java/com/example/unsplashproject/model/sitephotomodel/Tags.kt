package com.example.unsplashproject.model.sitephotomodel

import com.google.gson.annotations.SerializedName


data class Tags (

  @SerializedName("custom"     ) var custom     : ArrayList<Custom>     = arrayListOf(),
  @SerializedName("aggregated" ) var aggregated : ArrayList<Aggregated> = arrayListOf()

)