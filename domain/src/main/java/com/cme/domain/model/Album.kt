package com.cme.domain.model

import java.io.Serializable

data class Album(
    val id: String?,
    val artistName: String?,
    val artistUrl: String?,
    val name: String?,
    val releaseDate: String?,
    val albumUrl: String?,
    val albumImage: String?,
    val genre: List<Genre>?,
    val copyrightInfo: String?
): Serializable
