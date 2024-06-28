package com.cme.domain.model

import java.io.Serializable

data class Genre(
    val genreId: String?,
    val genreName: String?,
    val genreUrl: String?
): Serializable
