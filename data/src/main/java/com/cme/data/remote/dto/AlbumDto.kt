package com.cme.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AlbumDto(
    val id: String?,
    val artistName: String?,
    val artistUrl: String?,
    val name: String?,
    val releaseDate: String?,
    @SerializedName("url") val albumUrl: String?,
    @SerializedName("artworkUrl100") val albumImage: String?,
    @SerializedName("genres") val genreEntities: List<GenreDto>?,
)