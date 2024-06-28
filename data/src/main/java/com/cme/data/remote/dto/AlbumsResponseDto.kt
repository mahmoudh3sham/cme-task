package com.cme.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AlbumsResponseDto(
    @SerializedName("feed") val feed: AlbumsDataFeedDto,
)