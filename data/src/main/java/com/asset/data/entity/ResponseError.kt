/*
 * Copyright (c) DAR Ecosystem 2018.
 *
 * Created by sough on 10/07/2018.
 */

package com.asset.data.entity

import com.google.gson.annotations.SerializedName

/**
 *
 */
data class ResponseError(
        @SerializedName("status") val status: String,
        @SerializedName("message") val message: String,
        @SerializedName("developerMessage") val developerMessage: String
)