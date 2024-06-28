package com.cme.domain.usecase

import com.cme.domain.model.Album
import com.cme.domain.repo.AlbumsRepo

class GetAlbumsUseCase (private val albumsRepo: AlbumsRepo){

    suspend fun getAlbums(): List<Album> {
        return albumsRepo.getAlbumsFromRemote()
    }
}

