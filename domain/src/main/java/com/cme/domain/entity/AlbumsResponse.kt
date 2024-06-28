package com.cme.domain.entity

import com.google.gson.annotations.SerializedName

data class AlbumsResponse(
    @SerializedName("feed") val feed: AlbumsList,
    @SerializedName("copyright") val copyrightInfo: String
)