package com.oleksandrlysun.traveminder.presentation.screens.tabs.di

import androidx.navigation.fragment.findNavController
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
            return TabsNavigationImpl(fragment.findNavController())
        }
    }
}