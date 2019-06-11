package com.oleksandrlysun.traveminder.domain.interactors.impl

import com.oleksandrlysun.traveminder.domain.interactors.StorageInteractor
import com.oleksandrlysun.traveminder.domain.models.CameraNote
import com.oleksandrlysun.traveminder.domain.repositories.CameraNoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class StorageInteractorImpl(private val cameraNoteRepository: CameraNoteRepository) : StorageInteractor {

	override suspend fun add(entity: Any) {
		val repository = when (entity) {
			is CameraNote -> cameraNoteRepository
			else -> throw IllegalArgumentException("Unknown entity type: ${entity::class}")
		}

		withContext(Dispatchers.IO) { repository.add(entity) }
	}
}