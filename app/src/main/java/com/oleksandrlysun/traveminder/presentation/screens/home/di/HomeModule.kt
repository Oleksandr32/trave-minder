package com.oleksandrlysun.traveminder.presentation.screens.home.di

import com.oleksandrlysun.traveminder.presentation.di.scope.FragmentScope
import com.oleksandrlysun.traveminder.presentation.screens.home.HomeFragment
import com.oleksandrlysun.traveminder.presentation.screens.home.HomeView
import dagger.Binds
import dagger.Module

@Module
abstract class HomeModule {

	@FragmentScope
	@Binds
	abstract fun bindView(fragment: HomeFragment): HomeView
}