package com.cme.data.repo

import android.net.ConnectivityManager
import android.util.Log
import com.cme.data.remote.ApiService
import com.cme.data.utils.ConnectivityChecker
import com.cme.domain.model.Album
import com.cme.domain.repo.AlbumsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AlbumsRepoImpl (private val apiService: ApiService, private val cm: ConnectivityManager) : AlbumsRepo {
    private val mTAG = "AlbumsRepoImpl"

    override fun getAlbums(): Flow<MutableList<Album>>{
        return if (ConnectivityChecker.isNetworkConnected(cm)) {
            Log.e(mTAG, "subscribeViewModel: Yes Connection")
            getAlbumsFromRemote()
        } else {
            Log.e(mTAG, "subscribeViewModel: No Connection")
            getAlbumsFromLocal()
        }
    }

    private fun getAlbumsFromRemote(): Flow<MutableList<Album>> {
        return flow {
            emit(AlbumMapper.mapAlbumDataEntityToAlbumDomainModel(apiService.getAlbums()))
        }
    }

    private fun getAlbumsFromLocal(): Flow<MutableList<Album>> {
        return flow {
            emit(AlbumMapper.mapAlbumDataEntityToAlbumDomainModel(apiService.getAlbums()))
        }
    }


}