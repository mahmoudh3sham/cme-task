package com.cme.data.mapper

import com.cme.data.local.entity.AlbumDbEntity
import com.cme.data.local.entity.GenreDbEntity
import com.cme.data.remote.dto.AlbumDto
import com.cme.data.remote.dto.AlbumsResponseDto
import com.cme.domain.model.Album
import com.cme.domain.model.Genre
import io.realm.kotlin.ext.realmListOf

object AlbumMapper {

    /**
     * @mapAlbumDtoToAlbumModel is responsible for mapping the remote Album Dto response from remote
     * to Album domain model for viewModel usage.
     */
    fun mapAlbumDtoToAlbumModel(albumsResponseDto: AlbumsResponseDto?) : MutableList<Album>{

        if (albumsResponseDto?.feed?.albumsList.isNullOrEmpty()) return mutableListOf()

        val copyRightInfo = albumsResponseDto?.feed?.copyrightInfo ?: ""

        val albums = mutableListOf<Album>()
        albumsResponseDto?.feed?.albumsList?.forEach { it ->
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

    /**
     * @mapAlbumEntityToAlbumModel is responsible for mapping the local Album Entity from local
     * to Album domain model for viewModel offline usage.
     */
    fun mapAlbumEntityToAlbumModel(albumsDbEntity: List<AlbumDbEntity>) : MutableList<Album>{

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

    /**
     * @mapAlbumDtoToAlbumEntity is responsible for mapping the remote Album Dto from remote
     * to Album Entity model for caching in database.
     */
    fun mapAlbumDtoToAlbumEntity(remoteAlbum: AlbumDto, mCopyRightInfo: String?) : AlbumDbEntity {
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