package com.cme.data.remote.entity

import com.google.gson.annotations.SerializedName

data class AlbumsResponse(
    @SerializedName("feed") val feed: AlbumsDataFeed,
)