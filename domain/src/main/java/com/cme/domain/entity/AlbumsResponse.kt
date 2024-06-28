package com.cme.domain.entity

import com.google.gson.annotations.SerializedName

data class AlbumsResponse(
    @SerializedName("feed") val feed: AlbumsData,
)