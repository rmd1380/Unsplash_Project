package com.example.example

import com.google.gson.annotations.SerializedName


data class TopicSubmissions (

  @SerializedName("street-photography" ) var street-photography : Street-photography? = Street-photography(),
  @SerializedName("current-events"     ) var current-events     : Current-events?     = Current-events()

)