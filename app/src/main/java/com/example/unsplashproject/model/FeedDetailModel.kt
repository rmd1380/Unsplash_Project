package com.example.unsplashproject.model

import java.text.DateFormat

data class FeedDetailModel(
    val photographer:String,
    val viewCount:Int,
    val downloadCount:Int,
    val publishDate:DateFormat,
    val cameraName:String

)