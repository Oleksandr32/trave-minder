package com.oleksandrlysun.traveminder.presentation.screens.maps.di

import com.oleksandrlysun.traveminder.presentation.screens.maps.MapsFragment
import com.oleksandrlysun.traveminder.presentation.screens.maps.MapsView
import dagger.Binds
import dagger.Module

@Module
abstract class MapsModule {

	@Binds
	abstract fun bindView(fragment: MapsFragment): MapsView
}