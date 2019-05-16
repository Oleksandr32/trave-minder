package com.oleksandrlysun.traveminder.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

	@Singleton
	@Provides
	fun provideContext(): Context = application
}