package com.cme.data.local

import com.cme.data.local.entity.AlbumDbEntity
import com.cme.data.remote.entity.AlbumsResponse
import com.cme.data.repo.AlbumMapper
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RealmLocalManager (private val realm: Realm) {

    fun getAlbumsFromDb(): Flow<List<AlbumDbEntity>>{
        return realm.query<AlbumDbEntity>()
            .asFlow()
            .map { results ->
                results.list.toList()
            }
    }

    suspend fun insertAlbumsIntoDb(albumsResponse: AlbumsResponse) {
        val mCopyRightInfo = albumsResponse.feed.copyrightInfo ?: ""
        albumsResponse.feed.albumsList?.forEach { remoteAlbum ->
            val localAlbum = AlbumMapper.mapRemoteAlbumToLocalDbAlbum(remoteAlbum, mCopyRightInfo)
            realm.write {
                copyToRealm(localAlbum, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }
}