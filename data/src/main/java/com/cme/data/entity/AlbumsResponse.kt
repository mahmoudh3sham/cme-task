package com.cme.data.entity

import com.google.gson.annotations.SerializedName

data class AlbumsResponse(
    @SerializedName("feed") val feed: AlbumsDataFeed,
)