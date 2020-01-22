package com.asset.domain.entity

data class PhotoDomain(val albumId: Int,
                       val id: Int,
                       val title: String,
                       val url: String,
                       val thumbnailUrl: String)