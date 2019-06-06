package com.oleksandrlysun.traveminder.data.database.mapper.impl

import com.oleksandrlysun.traveminder.data.database.mapper.EntityMapper
import com.oleksandrlysun.traveminder.data.database.model.RealmCameraNote
import com.oleksandrlysun.traveminder.data.utils.DateUtil
import com.oleksandrlysun.traveminder.data.utils.toRealmList
import com.oleksandrlysun.traveminder.domain.models.CameraNote
import java.io.File

class CameraNoteMapper : EntityMapper<RealmCameraNote, CameraNote> {

	override fun mapToEntity(realmModel: RealmCameraNote): CameraNote {
		return CameraNote(
				realmModel.title,
				realmModel.tags?.toList(),
				realmModel.description,
				realmModel.date?.let { DateUtil.parseDate(it) },
				File(realmModel.picturePath)
		)
	}

	override fun mapToRealmModel(entity: CameraNote): RealmCameraNote {
		return RealmCameraNote(
				title = entity.title,
				tags = entity.tags?.toRealmList(),
				description = entity.description,
				date = entity.date?.let { DateUtil.formatDate(it) },
				picturePath = entity.picture.absolutePath
		)
	}
}