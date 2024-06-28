package com.cme.data.repo

import com.cme.data.entity.AlbumsResponse
import com.cme.domain.model.Album
import com.cme.domain.model.Genre

object AlbumMapper {

    fun mapAlbumDataEntityToAlbumDomainModel(albumsResponse: AlbumsResponse?) : MutableList<Album>{
        val albums = mutableListOf<Album>()

        if (albumsResponse?.feed?.albumsList.isNullOrEmpty()) return albums

        val copyRightInfo = albumsResponse?.feed?.copyrightInfo

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
}