package com.cme.data.remote.entity

import com.google.gson.annotations.SerializedName

data class GenreEntity(
    val genreId: String?,
    @SerializedName("name") val genreName: String?,
    @SerializedName("url") val genreUrl: String?
)