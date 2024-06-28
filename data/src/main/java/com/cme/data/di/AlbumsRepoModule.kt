package com.cme.data.di

import android.net.ConnectivityManager
import com.cme.data.local.RealmLocalManager
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
    fun provideAlbumsRepo(apiService: ApiService, localDb: RealmLocalManager, cm: ConnectivityManager): AlbumsRepo{
        return AlbumsRepoImpl(apiService, localDb, cm)
    }
}