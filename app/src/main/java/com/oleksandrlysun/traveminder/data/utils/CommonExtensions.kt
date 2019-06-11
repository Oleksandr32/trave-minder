package com.oleksandrlysun.traveminder.data.utils

import io.realm.RealmList

fun <E> List<E>.toRealmList(): RealmList<E> {
	val list: RealmList<E> = RealmList()
	forEach { list.add(it) }
	return list
}