package com.cme.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
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
): Parcelable
