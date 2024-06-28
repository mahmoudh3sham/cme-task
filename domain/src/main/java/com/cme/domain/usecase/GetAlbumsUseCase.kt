package com.cme.domain.usecase

import com.cme.domain.entity.AlbumsResponse
import com.cme.domain.repo.AlbumsRepo

class GetAlbumsUseCase (private val albumsRepo: AlbumsRepo){

    suspend fun getAlbums(): AlbumsResponse{
        return albumsRepo.getAlbumsFromRemote()
    }
}

