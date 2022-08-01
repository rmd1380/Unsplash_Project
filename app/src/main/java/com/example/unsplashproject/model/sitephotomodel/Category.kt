package com.example.example

import com.google.gson.annotations.SerializedName


data class Category (

  @SerializedName("slug"        ) var slug       : String? = null,
  @SerializedName("pretty_slug" ) var prettySlug : String? = null

)