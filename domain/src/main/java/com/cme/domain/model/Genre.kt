package com.cme.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val genreId: String?,
    val genreName: String?,
    val genreUrl: String?
): Parcelable
