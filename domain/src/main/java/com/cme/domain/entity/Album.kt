package com.cme.domain.entity

import com.google.gson.annotations.SerializedName

data class Album(
    val id: String?,
    val artistName: String?,
    val artistUrl: String?,
    val name: String?,
    val releaseDate: String?,
    @SerializedName("url") val albumUrl: String?,
    @SerializedName("artworkUrl100") val albumImage: String?,
    val genres: List<Genre>?
)