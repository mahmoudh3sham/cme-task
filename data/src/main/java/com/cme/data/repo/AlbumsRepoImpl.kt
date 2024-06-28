package com.cme.data.repo

import com.cme.data.remote.ApiService
import com.cme.domain.model.Album
import com.cme.domain.repo.AlbumsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AlbumsRepoImpl (private val apiService: ApiService) : AlbumsRepo {

    override fun getAlbumsFromRemote(): Flow<MutableList<Album>> {
        return flow {
            emit(AlbumMapper.mapAlbumDataEntityToAlbumDomainModel(apiService.getAlbums()))
        }
    }
}