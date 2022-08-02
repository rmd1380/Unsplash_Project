package com.example.example

import com.google.gson.annotations.SerializedName


data class Current-events (

  @SerializedName("status"      ) var status     : String? = null,
  @SerializedName("approved_on" ) var approvedOn : String? = null

)