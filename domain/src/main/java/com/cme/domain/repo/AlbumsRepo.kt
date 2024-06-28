package com.cme.domain.repo

import com.cme.domain.model.Album


interface AlbumsRepo {
    suspend fun getAlbumsFromRemote(): List<Album>
}