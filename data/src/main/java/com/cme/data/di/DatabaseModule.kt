package com.cme.data.di

import com.cme.data.local.RealmLocalManager
import com.cme.data.local.entity.AlbumDbEntity
import com.cme.data.local.entity.GenreDbEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.create(schema = setOf(AlbumDbEntity::class, GenreDbEntity::class))
    }

    @Provides
    @Singleton
    fun provideRealmInstance(realmConfiguration: RealmConfiguration): Realm {
        return Realm.open(realmConfiguration)
    }

    @Provides
    @Singleton
    fun provideLocalDbOperation(realm: Realm): RealmLocalManager {
        return RealmLocalManager(realm)
    }
}