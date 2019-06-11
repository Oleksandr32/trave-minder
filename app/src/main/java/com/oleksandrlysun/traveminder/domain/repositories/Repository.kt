package com.oleksandrlysun.traveminder.domain.repositories

interface Repository<Entity> {

	suspend fun add(entity: Entity)

	suspend fun delete(id: String)

	suspend fun get(id: String): Entity

	suspend fun getAll(): List<Entity>
}