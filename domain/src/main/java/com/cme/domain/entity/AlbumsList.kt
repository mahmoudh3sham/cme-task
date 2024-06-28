package com.cme.domain.entity

import com.google.gson.annotations.SerializedName

data class AlbumsList(
    @SerializedName("results") val albumsList: List<Album>
)