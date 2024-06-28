package com.cme.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AlbumsDataFeedDto(
    @SerializedName("results") val albumsList: List<AlbumDto>?,
    @SerializedName("copyright") val copyrightInfo: String?
)