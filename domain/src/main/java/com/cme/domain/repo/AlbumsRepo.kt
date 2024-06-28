package com.cme.domain.repo

import com.cme.domain.model.Album
import kotlinx.coroutines.flow.Flow


interface AlbumsRepo {
    fun getAlbums(): Flow<MutableList<Album>>
}