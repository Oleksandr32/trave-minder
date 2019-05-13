package com.oleksandrlysun.traveminder.presentation.screens.di

import androidx.navigation.Navigation
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.di.scope.ActivityScope
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import com.oleksandrlysun.traveminder.presentation.navigation.impl.MainNavigationImpl
import com.oleksandrlysun.traveminder.presentation.screens.MainActivity
import dagger.Module
import dagger.Provides

@Module(includes = [MainFragmentBindingModule::class])
abstract class MainModule {

	@Module
	companion object {

		@JvmStatic
		@ActivityScope
		@Provides
		fun provideMainNavigation(activity: MainActivity): MainNavigation {
			return MainNavigationImpl(Navigation.findNavController(activity, R.id.root_navigation_host_fragment))
		}
	}
}