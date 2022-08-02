package com.example.unsplashproject.model.sitetopicmodel

import com.google.gson.annotations.SerializedName


data class TopicSubmissions (

  @SerializedName("street-photography" ) var streetphotography : Streetphotography? = Streetphotography(),
  @SerializedName("current-events"     ) var currentevents     : Currentevents?     = Currentevents()

)