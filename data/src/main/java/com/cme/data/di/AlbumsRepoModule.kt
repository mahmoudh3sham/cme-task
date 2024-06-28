package com.cme.data.di

import com.cme.data.remote.ApiService
import com.cme.data.repo.AlbumsRepoImpl
import com.cme.domain.repo.AlbumsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AlbumsRepoModule {

    @Provides
    fun provideAlbumsRepo(apiService: ApiService): AlbumsRepo{
        return AlbumsRepoImpl(apiService)
    }
}