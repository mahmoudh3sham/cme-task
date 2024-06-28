package com.cme.domain.usecase

import com.cme.domain.model.Album
import com.cme.domain.repo.AlbumsRepo
import kotlinx.coroutines.flow.Flow

class GetAlbumsUseCase (private val albumsRepo: AlbumsRepo){

    fun getAlbums(): Flow<MutableList<Album>> {
        return albumsRepo.getAlbumsFromRemote()
    }
}

