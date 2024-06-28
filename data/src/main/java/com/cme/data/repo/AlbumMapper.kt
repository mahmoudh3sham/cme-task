package com.cme.data.repo

import com.cme.data.local.entity.AlbumDbEntity
import com.cme.data.local.entity.GenreDbEntity
import com.cme.data.remote.entity.AlbumEntity
import com.cme.data.remote.entity.AlbumsResponse
import com.cme.domain.model.Album
import com.cme.domain.model.Genre
import io.realm.kotlin.ext.realmListOf

object AlbumMapper {

    fun mapAlbumRemoteDataEntityToAlbumDomainModel(albumsResponse: AlbumsResponse?) : MutableList<Album>{

        if (albumsResponse?.feed?.albumsList.isNullOrEmpty()) return mutableListOf()

        val copyRightInfo = albumsResponse?.feed?.copyrightInfo ?: ""

        val albums = mutableListOf<Album>()
        albumsResponse?.feed?.albumsList?.forEach { it ->
            val genres = mutableListOf<Genre>()
            it.genreEntities?.forEach {
                val genre = Genre(it.genreId, it.genreName, it.genreUrl)
                genres.add(genre)
            }

            val album = Album(
                it.id,
                it.artistName,
                it.artistUrl,
                it.name,
                it.releaseDate,
                it.albumUrl,
                it.albumImage,
                genres,
                copyRightInfo
            )
            albums.add(album)
        }
        return albums

    }

    fun mapAlbumLocalDataEntityToAlbumDomainModel(albumsDbEntity: List<AlbumDbEntity>) : MutableList<Album>{

        if (albumsDbEntity.isEmpty()) return mutableListOf()

        val albums = mutableListOf<Album>()
        albumsDbEntity.forEach { it ->
            val genres = mutableListOf<Genre>()
            it.genreEntities.forEach {
                val genre = Genre(it.genreId, it.genreName, it.genreUrl)
                genres.add(genre)
            }

            val album = Album(
                it.id,
                it.artistName,
                it.artistUrl,
                it.name,
                it.releaseDate,
                it.albumUrl,
                it.albumImage,
                genres,
                it.copyRightInfo
            )
            albums.add(album)
        }
        return albums

    }

    fun mapRemoteAlbumToLocalDbAlbum(remoteAlbum: AlbumEntity, mCopyRightInfo: String?) : AlbumDbEntity {
        val localGenres = realmListOf<GenreDbEntity>()
        remoteAlbum.genreEntities?.forEach { remoteGenre->
            val localGenre = GenreDbEntity()
            localGenre.genreId = remoteGenre.genreId ?: ""
            localGenre.genreName = remoteGenre.genreName ?: ""
            localGenre.genreUrl = remoteGenre.genreUrl ?: ""
            localGenres.add(localGenre)
        }

        val localAlbum = AlbumDbEntity()
        localAlbum.id = remoteAlbum.id ?: ""
        localAlbum.artistName = remoteAlbum.artistName ?: ""
        localAlbum.artistUrl = remoteAlbum.artistUrl ?: ""
        localAlbum.name = remoteAlbum.name ?: ""
        localAlbum.releaseDate = remoteAlbum.releaseDate ?: ""
        localAlbum.albumUrl = remoteAlbum.albumUrl ?: ""
        localAlbum.albumImage = remoteAlbum.albumImage ?: ""
        localAlbum.genreEntities = localGenres
        localAlbum.copyRightInfo = mCopyRightInfo ?: ""

        return localAlbum
    }
}