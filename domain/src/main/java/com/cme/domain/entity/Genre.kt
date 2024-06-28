package com.cme.domain.entity

import com.google.gson.annotations.SerializedName

data class Genre(
    val genreId: String,
    @SerializedName("name") val genreName: String,
    @SerializedName("url") val genreUrl: String
)