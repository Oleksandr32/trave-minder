package com.oleksandrlysun.traveminder.domain.repositories

interface Repository<Entity> {

	fun add(entity: Entity)

	fun delete(id: String)

	fun get(id: String): Entity

	fun getAll(): List<Entity>
}