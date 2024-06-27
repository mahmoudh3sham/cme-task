package com.cme.data.remote

import com.cme.domain.entity.AlbumsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("api/v2/us/music/most-played/100/albums.json")
    suspend fun getAlbums(): AlbumsResponse
}