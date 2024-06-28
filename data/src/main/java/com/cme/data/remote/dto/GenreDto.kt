package com.cme.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GenreDto(
    val genreId: String?,
    @SerializedName("name") val genreName: String?,
    @SerializedName("url") val genreUrl: String?
)