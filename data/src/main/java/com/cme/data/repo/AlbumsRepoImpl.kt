package com.cme.data.repo

import android.net.ConnectivityManager
import android.util.Log
import com.cme.data.local.RealmLocalManager
import com.cme.data.remote.ApiService
import com.cme.data.utils.ConnectivityChecker
import com.cme.domain.model.Album
import com.cme.domain.repo.AlbumsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class AlbumsRepoImpl (private val apiService: ApiService, private val localDataManager: RealmLocalManager, private val cm: ConnectivityManager) : AlbumsRepo {
    private val mTAG = "AlbumsRepoImpl"

    override fun getAlbums(): Flow<MutableList<Album>>{
        return if (ConnectivityChecker.isNetworkAvailable(cm)) {
            Log.e(mTAG, "subscribeViewModel: Yes Connection")
            getAlbumsFromRemote()
        } else {
            Log.e(mTAG, "subscribeViewModel: No Connection")
            getAlbumsFromLocal()
        }
    }

    private fun getAlbumsFromRemote(): Flow<MutableList<Album>> {
        return flow {
            val remoteAlbumsData = apiService.getAlbums()
            localDataManager.insertAlbumsIntoDb(remoteAlbumsData)
            emit(AlbumMapper.mapAlbumRemoteDataEntityToAlbumDomainModel(remoteAlbumsData))
        }
    }

    private fun getAlbumsFromLocal(): Flow<MutableList<Album>> {
        return flow {
            localDataManager.getAlbumsFromDb().collect{ results ->
                emit(AlbumMapper.mapAlbumLocalDataEntityToAlbumDomainModel(results))
            }
        }
    }


}