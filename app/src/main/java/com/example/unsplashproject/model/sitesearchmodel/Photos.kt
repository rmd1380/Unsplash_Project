package com.example.unsplashproject.model.sitesearchmodel

import com.google.gson.annotations.SerializedName

data class Photos (

    @SerializedName("id"         ) var id        : String? = null,
    @SerializedName("created_at" ) var createdAt : String? = null,
    @SerializedName("updated_at" ) var updatedAt : String? = null,
    @SerializedName("blur_hash"  ) var blurHash  : String? = null,
    @SerializedName("urls"       ) var urls      : Urls?   = Urls()

)