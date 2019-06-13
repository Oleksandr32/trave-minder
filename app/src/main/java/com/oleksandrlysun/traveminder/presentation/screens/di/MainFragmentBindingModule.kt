package com.oleksandrlysun.traveminder.presentation.screens.di

import com.oleksandrlysun.traveminder.presentation.di.scope.FragmentScope
import com.oleksandrlysun.traveminder.presentation.screens.camera.CameraFragment
import com.oleksandrlysun.traveminder.presentation.screens.camera.CreateCameraNoteFragment
import com.oleksandrlysun.traveminder.presentation.screens.camera.di.CameraModule
import com.oleksandrlysun.traveminder.presentation.screens.cameranotes.CameraNotesFragment
import com.oleksandrlysun.traveminder.presentation.screens.cameranotes.di.CameraNotesModule
import com.oleksandrlysun.traveminder.presentation.screens.tabs.TabsFragment
import com.oleksandrlysun.traveminder.presentation.screens.home.HomeFragment
import com.oleksandrlysun.traveminder.presentation.screens.home.di.HomeModule
import com.oleksandrlysun.traveminder.presentation.screens.permission.PermissionFragment
import com.oleksandrlysun.traveminder.presentation.screens.tabs.di.TabsModule
import com.oleksandrlysun.traveminder.presentation.screens.welcome.WelcomeFragment
import com.oleksandrlysun.traveminder.presentation.screens.welcome.di.WelcomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainFragmentBindingModule {

	@FragmentScope
	@ContributesAndroidInjector(modules = [WelcomeModule::class])
	fun contributeWelcomeFragmentInjector(): WelcomeFragment

	@FragmentScope
	@ContributesAndroidInjector(modules = [TabsModule::class])
	fun contributeTabsFragmentInjector(): TabsFragment

	@FragmentScope
	@ContributesAndroidInjector(modules = [HomeModule::class])
	fun contributeHomeFragmentInjector(): HomeFragment

	@FragmentScope
	@ContributesAndroidInjector
	fun contributePermissionFragmentInjector(): PermissionFragment

	@FragmentScope
	@ContributesAndroidInjector(modules = [CameraNotesModule::class])
	fun contributeCameraNotesFragmentInjector(): CameraNotesFragment

	@FragmentScope
	@ContributesAndroidInjector(modules = [CameraModule::class])
	fun contributeCameraFragmentInjector(): CameraFragment

	@FragmentScope
	@ContributesAndroidInjector(modules = [CameraModule::class])
	fun contributeCreateCameraNoteFragmentInjector(): CreateCameraNoteFragment
}