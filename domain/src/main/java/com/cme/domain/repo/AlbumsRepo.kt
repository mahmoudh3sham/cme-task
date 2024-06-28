package com.cme.domain.repo

import com.cme.domain.entity.AlbumsResponse

interface AlbumsRepo {
    suspend fun getAlbumsFromRemote(): AlbumsResponse
}