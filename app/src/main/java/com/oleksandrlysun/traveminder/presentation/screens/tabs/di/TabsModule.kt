package com.oleksandrlysun.traveminder.presentation.screens.tabs.di

import androidx.navigation.Navigation
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.di.scope.FragmentScope
import com.oleksandrlysun.traveminder.presentation.navigation.TabsNavigation
import com.oleksandrlysun.traveminder.presentation.navigation.impl.TabsNavigationImpl
import com.oleksandrlysun.traveminder.presentation.screens.tabs.TabsFragment
import dagger.Module
import dagger.Provides

@Module
abstract class TabsModule {

	@Module
	companion object {

		@JvmStatic
		@FragmentScope
		@Provides
		fun provideTabsNavigation(fragment: TabsFragment): TabsNavigation {
			val navController = Navigation.findNavController(fragment.requireActivity(), R.id.tabs_navigation_host_fragment)
			return TabsNavigationImpl(navController)
		}
	}
}