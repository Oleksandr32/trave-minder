package com.oleksandrlysun.traveminder.data.database.model

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.UUID

@RealmClass
open class RealmCameraNote(@PrimaryKey
                           var id: String = UUID.randomUUID().toString(),
                           var title: String = "",
                           var tags: RealmList<String>? = null,
                           var description: String? = null,
                           var date: String? = null,
                           var picturePath: String? = null) : RealmModel