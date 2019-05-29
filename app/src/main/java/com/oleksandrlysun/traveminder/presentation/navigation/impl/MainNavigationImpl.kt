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

	override fun tabsToMaps() {
		navController.navigate(R.id.action_tabsFragment_to_mapsFragment)
	}

	override fun tabsToCamera() {
		navController.navigate(R.id.action_tabsFragment_to_cameraFragment)
	}

	override fun cameraToCreateCameraNote() {
		navController.navigate(R.id.action_cameraFragment_to_createCameraNoteFragment)
	}

	override fun toPermission() {
		navController.navigate(R.id.action_to_permissionFragment)
	}
}