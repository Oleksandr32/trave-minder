package com.oleksandrlysun.traveminder.data.repositories

import com.oleksandrlysun.traveminder.data.database.mapper.EntityMapper
import com.oleksandrlysun.traveminder.domain.repositories.Repository
import io.realm.RealmModel

abstract class RealmRepository<R : RealmModel, T>(private val mapper: EntityMapper<R, T>) : Repository<T> {

	override fun add(entity: T) {
		TODO()
	}

	override fun delete(id: String) {
		TODO()
	}

	override fun get(id: String): T {
		TODO()
	}

	override fun getAll(): List<T> {
		TODO()
	}
}