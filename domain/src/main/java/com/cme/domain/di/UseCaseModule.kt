package com.cme.domain.di

import com.cme.domain.repo.AlbumsRepo
import com.cme.domain.usecase.GetAlbumsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAlbumsUseCase(albumsRepo: AlbumsRepo): GetAlbumsUseCase {
        return GetAlbumsUseCase(albumsRepo)
    }
}