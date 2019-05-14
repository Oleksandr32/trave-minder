package com.oleksandrlysun.traveminder.presentation.screens.welcome.di

import android.content.Context
import com.oleksandrlysun.traveminder.presentation.di.scope.FragmentScope
import com.oleksandrlysun.traveminder.presentation.screens.welcome.FeatureItemProvider
import dagger.Module
import dagger.Provides

@Module
abstract class WelcomeModule {

	@Module
	companion object {
		@JvmStatic
		@FragmentScope
		@Provides
		fun provideFeatureItemProvider(context: Context): FeatureItemProvider {
			return FeatureItemProvider(context)
		}
	}
}