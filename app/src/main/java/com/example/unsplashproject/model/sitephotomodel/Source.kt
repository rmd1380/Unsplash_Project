package com.example.unsplashproject.model.sitephotomodel

import com.example.unsplashproject.model.sitephotomodel.Ancestry
import com.google.gson.annotations.SerializedName


data class Source (

    @SerializedName("ancestry"         ) var ancestry        : Ancestry?   = Ancestry(),
    @SerializedName("title"            ) var title           : String?     = null,
    @SerializedName("subtitle"         ) var subtitle        : String?     = null,
    @SerializedName("description"      ) var description     : String?     = null,
    @SerializedName("meta_title"       ) var metaTitle       : String?     = null,
    @SerializedName("meta_description" ) var metaDescription : String?     = null,
    @SerializedName("cover_photo"      ) var coverPhoto      : CoverPhoto? = CoverPhoto()

)