package com.oleksandrlysun.traveminder.presentation.screens.di

import com.oleksandrlysun.traveminder.presentation.di.scope.FragmentScope
import com.oleksandrlysun.traveminder.presentation.screens.welcome.WelcomeFragment
import com.oleksandrlysun.traveminder.presentation.screens.welcome.di.WelcomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainFragmentBindingModule {

	@FragmentScope
	@ContributesAndroidInjector(modules = [WelcomeModule::class])
	fun contributeWelcomeFragmentInjector(): WelcomeFragment
}