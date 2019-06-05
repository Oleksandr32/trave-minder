package com.oleksandrlysun.traveminder.presentation.screens.camera.di

import com.oleksandrlysun.traveminder.presentation.di.scope.FragmentScope
import com.oleksandrlysun.traveminder.presentation.screens.FragmentStateHolder
import com.oleksandrlysun.traveminder.presentation.screens.camera.*
import com.oleksandrlysun.traveminder.utils.lazy.ResettableLazyManager
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CameraModule {

	@Binds
	abstract fun bindCameraView(fragment: CameraFragment): CameraView

	@Binds
	abstract fun bindCreateCameraNoteView(fragment: CreateCameraNoteFragment): CreateCameraNoteView

	@Module
	companion object {

		@JvmStatic
		@FragmentScope
		@Provides
		fun provideState(fragmentStateHolder: FragmentStateHolder): CameraFlowState {
			val state = fragmentStateHolder.state as? CameraFlowState ?: CameraFlowState()
			fragmentStateHolder.state = state
			return state
		}
	}
}