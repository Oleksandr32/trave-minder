package com.oleksandrlysun.traveminder.presentation.screens.camera.di

import com.oleksandrlysun.traveminder.presentation.screens.camera.*
import dagger.Binds
import dagger.Module

@Module
abstract class CameraModule {

	@Binds
	abstract fun bindCameraView(fragment: CameraFragment): CameraView

	@Binds
	abstract fun bindCreateCameraNoteView(fragment: CreateCameraNoteFragment): CreateCameraNoteView
}