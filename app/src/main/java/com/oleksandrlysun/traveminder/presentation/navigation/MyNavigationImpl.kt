package com.oleksandrlysun.traveminder.presentation.navigation

import androidx.annotation.IdRes
import androidx.navigation.NavController
import com.oleksandrlysun.traveminder.R

class MyNavigationImpl(private val navController: NavController) : MyNavigation {

	override fun navigateUp(): Boolean {
		return navController.navigateUp()
	}

	override fun welcomeToHome() {
		navigate(R.id.action_welcomeFragment_to_mapsFragment)
	}

	private fun navigate(@IdRes action: Int) {
		navController.navigate(action)
	}
}