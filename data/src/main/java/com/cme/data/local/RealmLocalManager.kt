package com.cme.data.local

import com.cme.data.local.entity.AlbumDbEntity
import com.cme.data.remote.dto.AlbumsResponseDto
import com.cme.data.mapper.AlbumMapper
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

    suspend fun insertAlbumsIntoDb(albumsResponseDto: AlbumsResponseDto) {
        val mCopyRightInfo = albumsResponseDto.feed.copyrightInfo ?: ""
        albumsResponseDto.feed.albumsList?.forEach { remoteAlbum ->
            val localAlbum = AlbumMapper.mapAlbumDtoToAlbumEntity(remoteAlbum, mCopyRightInfo)
            realm.write {
                copyToRealm(localAlbum, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }
}