package com.oleksandrlysun.traveminder.data.repositories.impl

import com.oleksandrlysun.traveminder.data.database.mapper.impl.CameraNoteMapper
import com.oleksandrlysun.traveminder.data.database.model.RealmCameraNote
import com.oleksandrlysun.traveminder.data.repositories.RealmRepository
import com.oleksandrlysun.traveminder.domain.models.CameraNote
import com.oleksandrlysun.traveminder.domain.repositories.CameraNoteRepository

class CameraNoteRepositoryImpl(mapper: CameraNoteMapper) :
		RealmRepository<RealmCameraNote, CameraNote>(mapper),
		CameraNoteRepository