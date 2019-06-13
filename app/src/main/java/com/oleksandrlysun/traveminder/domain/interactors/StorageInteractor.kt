package com.oleksandrlysun.traveminder.domain.interactors

import com.oleksandrlysun.traveminder.domain.models.CameraNote

interface StorageInteractor {

	suspend fun add(entity: Any)

	suspend fun getAll(): List<CameraNote>
}