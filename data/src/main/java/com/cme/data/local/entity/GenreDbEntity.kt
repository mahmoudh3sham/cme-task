package com.cme.data.local.entity

import io.realm.kotlin.types.EmbeddedRealmObject

class GenreDbEntity: EmbeddedRealmObject {
    var genreId: String = ""
    var genreName: String = ""
    var genreUrl: String = ""
}