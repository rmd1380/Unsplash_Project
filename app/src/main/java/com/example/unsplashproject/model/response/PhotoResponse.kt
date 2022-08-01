package com.example.example

import com.google.gson.annotations.SerializedName


data class ExampleJson2KtKotlin (

  @SerializedName("id"                       ) var id                     : String?             = null,
  @SerializedName("created_at"               ) var createdAt              : String?             = null,
  @SerializedName("updated_at"               ) var updatedAt              : String?             = null,
  @SerializedName("promoted_at"              ) var promotedAt             : String?             = null,
  @SerializedName("width"                    ) var width                  : Int?                = null,
  @SerializedName("height"                   ) var height                 : Int?                = null,
  @SerializedName("color"                    ) var color                  : String?             = null,
  @SerializedName("blur_hash"                ) var blurHash               : String?             = null,
  @SerializedName("description"              ) var description            : String?             = null,
  @SerializedName("alt_description"          ) var altDescription         : String?             = null,
  @SerializedName("urls"                     ) var urls                   : Urls?               = Urls(),
  @SerializedName("links"                    ) var links                  : Links?              = Links(),
  @SerializedName("categories"               ) var categories             : ArrayList<String>   = arrayListOf(),
  @SerializedName("likes"                    ) var likes                  : Int?                = null,
  @SerializedName("liked_by_user"            ) var likedByUser            : Boolean?            = null,
  @SerializedName("current_user_collections" ) var currentUserCollections : ArrayList<String>   = arrayListOf(),
  @SerializedName("sponsorship"              ) var sponsorship            : Sponsorship?        = Sponsorship(),
  @SerializedName("topic_submissions"        ) var topicSubmissions       : TopicSubmissions?   = TopicSubmissions(),
  @SerializedName("user"                     ) var user                   : User?               = User(),
  @SerializedName("exif"                     ) var exif                   : Exif?               = Exif(),
  @SerializedName("location"                 ) var location               : Location?           = Location(),
  @SerializedName("meta"                     ) var meta                   : Meta?               = Meta(),
  @SerializedName("public_domain"            ) var publicDomain           : Boolean?            = null,
  @SerializedName("tags"                     ) var tags                   : ArrayList<String>   = arrayListOf(),
  @SerializedName("tags_preview"             ) var tagsPreview            : ArrayList<String>   = arrayListOf(),
  @SerializedName("related_collections"      ) var relatedCollections     : RelatedCollections? = RelatedCollections(),
  @SerializedName("views"                    ) var views                  : Int?                = null,
  @SerializedName("downloads"                ) var downloads              : Int?                = null,
  @SerializedName("topics"                   ) var topics                 : ArrayList<String>   = arrayListOf()

)