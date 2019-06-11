package com.oleksandrlysun.traveminder.domain.interactors

interface StorageInteractor {

	suspend fun add(entity: Any)
}