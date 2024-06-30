package com.cme.domain.fake_repo

import com.cme.domain.model.Album
import com.cme.domain.repo.AlbumsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAlbumsRepo : AlbumsRepo{

    private val albums = mutableListOf<Album>(
        Album("1", "artist","artistUrl","name","date","albumUrl","imgUrl", listOf(),"Copy Right"),
        Album("2", "artist","artistUrl","name","date","albumUrl","imgUrl", listOf(),"Copy Right"),
    )

    override fun getAlbums(): Flow<MutableList<Album>> {
        return flow { emit(albums) }
    }
}