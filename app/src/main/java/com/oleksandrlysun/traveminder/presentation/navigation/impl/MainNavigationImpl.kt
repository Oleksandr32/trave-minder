package com.oleksandrlysun.traveminder.presentation.navigation.impl

import androidx.navigation.NavController
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation

class MainNavigationImpl(private val navController: NavController) : MainNavigation {

	override fun navigateUp(): Boolean {
		return navController.navigateUp()
	}

	override fun welcomeToTabs() {
		navController.navigate(R.id.action_welcomeFragment_to_tabsFragment)
	}
}