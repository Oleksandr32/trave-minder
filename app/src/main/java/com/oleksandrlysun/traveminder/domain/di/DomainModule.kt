package com.oleksandrlysun.traveminder.domain.di

import com.oleksandrlysun.traveminder.domain.interactors.StorageInteractor
import com.oleksandrlysun.traveminder.domain.interactors.impl.StorageInteractorImpl
import com.oleksandrlysun.traveminder.domain.repositories.CameraNoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

	@Singleton
	@Provides
	fun provideStorageInteractor(cameraNoteRepository: CameraNoteRepository): StorageInteractor {
		return StorageInteractorImpl(cameraNoteRepository)
	}
}