package com.oleksandrlysun.traveminder.data.database.mapper

import io.realm.RealmModel

interface EntityMapper<R : RealmModel, T> {

	fun mapToEntity(realmModel: R): T

	fun mapToRealmModel(entity: T): R
}