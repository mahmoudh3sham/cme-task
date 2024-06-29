package com.cme.data.local.entity

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class AlbumDbEntity : RealmObject {
    @PrimaryKey var id: String = ""
    var artistName: String = ""
    var artistUrl: String = ""
    var name: String = ""
    var releaseDate: String = ""
    var albumUrl: String = ""
    var albumImage: String = ""
    var genreEntities: RealmList<GenreDbEntity> = realmListOf()
    var copyRightInfo: String = ""
}