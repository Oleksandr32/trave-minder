package com.oleksandrlysun.traveminder.presentation.screens.di

import androidx.navigation.findNavController
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.di.scope.ActivityScope
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import com.oleksandrlysun.traveminder.presentation.navigation.impl.MainNavigationImpl
import com.oleksandrlysun.traveminder.presentation.screens.MainActivity
import com.oleksandrlysun.traveminder.presentation.screens.FragmentStateHolder
import com.oleksandrlysun.traveminder.presentation.screens.permission.PermissionResultLiveEvent
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
			return MainNavigationImpl(activity.findNavController(R.id.root_navigation_host_fragment)
			)
		}

		@JvmStatic
		@ActivityScope
		@Provides
		fun providePermissionResultLiveEvent() = PermissionResultLiveEvent()

		@JvmStatic
		@ActivityScope
		@Provides
		fun provideStateHolder() = FragmentStateHolder()
	}
}