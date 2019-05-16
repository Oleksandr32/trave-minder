package com.oleksandrlysun.traveminder.presentation.navigation.impl

import androidx.navigation.NavController
import com.oleksandrlysun.traveminder.presentation.navigation.TabsNavigation

class TabsNavigationImpl(private val navController: NavController) : TabsNavigation {

	override fun getNavController() = navController

}