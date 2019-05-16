package com.oleksandrlysun.traveminder.presentation.screens.camera.di

import com.oleksandrlysun.traveminder.presentation.di.scope.FragmentScope
import com.oleksandrlysun.traveminder.presentation.screens.camera.CameraFragment
import com.oleksandrlysun.traveminder.presentation.screens.camera.CameraView
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CameraModule {

	@FragmentScope
	@Binds
	abstract fun bindView(fragment: CameraFragment): CameraView
}