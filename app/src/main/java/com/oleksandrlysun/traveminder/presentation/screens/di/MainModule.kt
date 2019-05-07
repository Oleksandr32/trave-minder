package com.oleksandrlysun.traveminder.presentation.screens.di

import androidx.navigation.Navigation
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.di.scope.ActivityScope
import com.oleksandrlysun.traveminder.presentation.navigation.MyNavigation
import com.oleksandrlysun.traveminder.presentation.navigation.MyNavigationImpl
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
		fun provideNavigation(activity: MainActivity): MyNavigation {
			return MyNavigationImpl(Navigation.findNavController(activity, R.id.navigation_host_fragment))
		}
	}
}