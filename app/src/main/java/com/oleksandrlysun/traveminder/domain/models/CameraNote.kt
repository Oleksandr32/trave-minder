package com.oleksandrlysun.traveminder.domain.models

import java.util.UUID
import java.util.Date

data class CameraNote(var id: String = UUID.randomUUID().toString(),
                      var title: String,
                      var tags: List<String>? = null,
                      var description: String? = null,
                      var date: Date? = null,
                      var picturePath: String)