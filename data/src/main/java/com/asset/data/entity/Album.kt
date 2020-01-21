package com.asset.data.entity

import com.google.gson.annotations.SerializedName

data class Album(@SerializedName("id") val id: Int,
                 @SerializedName("title") val title: String)