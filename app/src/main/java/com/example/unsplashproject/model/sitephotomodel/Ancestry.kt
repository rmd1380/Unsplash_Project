package com.example.unsplashproject.model.sitephotomodel


import com.google.gson.annotations.SerializedName


data class Ancestry (

  @SerializedName("type"        ) var type        : Type?        = Type(),
  @SerializedName("category"    ) var category    : Category?    = Category(),
  @SerializedName("subcategory" ) var subcategory : Subcategory? = Subcategory()

)