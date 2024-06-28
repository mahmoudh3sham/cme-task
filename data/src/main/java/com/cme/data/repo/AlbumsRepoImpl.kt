package com.cme.data.repo

import com.cme.data.remote.ApiService
import com.cme.data.entity.AlbumsResponse
import com.cme.domain.repo.AlbumsRepo

class AlbumsRepoImpl (private val apiService: ApiService) : AlbumsRepo {

    override suspend fun getAlbumsFromRemote(): AlbumsResponse {
        return apiService.getAlbums()
    }
}