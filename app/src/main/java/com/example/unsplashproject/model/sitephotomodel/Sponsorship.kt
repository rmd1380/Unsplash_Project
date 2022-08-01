package com.example.example

import com.google.gson.annotations.SerializedName


data class Sponsorship (

  @SerializedName("impression_urls" ) var impressionUrls : ArrayList<String> = arrayListOf(),
  @SerializedName("tagline"         ) var tagline        : String?           = null,
  @SerializedName("tagline_url"     ) var taglineUrl     : String?           = null,
  @SerializedName("sponsor"         ) var sponsor        : Sponsor?          = Sponsor()

)