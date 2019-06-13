package com.oleksandrlysun.traveminder.presentation.screens.cameranotes.di

import com.oleksandrlysun.traveminder.presentation.screens.cameranotes.CameraNotesFragment
import com.oleksandrlysun.traveminder.presentation.screens.cameranotes.CameraNotesView
import dagger.Binds
import dagger.Module

@Module
abstract class CameraNotesModule {

	@Binds
	abstract fun bindView(fragment: CameraNotesFragment): CameraNotesView
}