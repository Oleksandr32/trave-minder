package com.oleksandrlysun.traveminder.presentation.di

import com.oleksandrlysun.traveminder.presentation.di.scope.ActivityScope
import com.oleksandrlysun.traveminder.presentation.screens.MainActivity
import com.oleksandrlysun.traveminder.presentation.screens.di.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {

	@ActivityScope
	@ContributesAndroidInjector(modules = [MainModule::class])
	fun contributeMainActivityInjector(): MainActivity
}