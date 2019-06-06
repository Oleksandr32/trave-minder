package com.oleksandrlysun.traveminder.domain.models

import java.io.File
import java.util.Date

data class CameraNote(var title: String,
                      var tags: List<String>? = null,
                      var description: String? = null,
                      var date: Date? = null,
                      var picture: File)