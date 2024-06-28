package com.cme.data.entity

import com.google.gson.annotations.SerializedName

data class AlbumsDataFeed(
    @SerializedName("results") val albumsList: List<AlbumEntity>?,
    @SerializedName("copyright") val copyrightInfo: String?
)