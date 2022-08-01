package com.example.example

import com.google.gson.annotations.SerializedName


data class Tags (

  @SerializedName("type"   ) var type   : String? = null,
  @SerializedName("title"  ) var title  : String? = null,
  @SerializedName("source" ) var source : Source? = Source()

)