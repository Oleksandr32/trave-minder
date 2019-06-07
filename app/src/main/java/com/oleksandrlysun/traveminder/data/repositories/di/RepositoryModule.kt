package com.oleksandrlysun.traveminder.data.repositories.di

import com.oleksandrlysun.traveminder.data.database.mapper.impl.CameraNoteMapper
import com.oleksandrlysun.traveminder.data.repositories.impl.CameraNoteRepositoryImpl
import com.oleksandrlysun.traveminder.domain.repositories.CameraNoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

	@Singleton
	@Provides
	fun provideCameraNoteRepository(mapper: CameraNoteMapper): CameraNoteRepository {
		return CameraNoteRepositoryImpl(mapper)
	}

	@Singleton
	@Provides
	fun provideCameraNoteMapper() = CameraNoteMapper()
}