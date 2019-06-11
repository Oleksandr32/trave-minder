package com.oleksandrlysun.traveminder.data.repositories

import com.oleksandrlysun.traveminder.data.database.mapper.EntityMapper
import com.oleksandrlysun.traveminder.domain.repositories.Repository
import io.realm.Realm
import io.realm.RealmModel

abstract class RealmRepository<R : RealmModel, T>(private val mapper: EntityMapper<R, T>) : Repository<T> {

	override suspend fun add(entity: T) {
		Realm.getDefaultInstance().use { realm ->
			realm.executeTransaction { r ->
				val model = mapper.mapToRealmModel(entity)
				r.insertOrUpdate(model)
			}
		}
	}

	override suspend fun delete(id: String) {
		TODO()
	}

	override suspend fun get(id: String): T {
		TODO()
	}

	override suspend fun getAll(): List<T> {
		TODO()
	}
}