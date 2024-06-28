package com.cme.domain.entity

import com.google.gson.annotations.SerializedName

data class AlbumsData(
    @SerializedName("results") val albumsList: List<Album>?,
    @SerializedName("copyright") val copyrightInfo: String?
)