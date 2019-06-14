package com.oleksandrlysun.traveminder.presentation.navigation.impl

import androidx.navigation.NavController
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import com.oleksandrlysun.traveminder.presentation.screens.camera.CameraFragmentDirections
import com.oleksandrlysun.traveminder.presentation.screens.permission.PermissionFragmentDirections

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

	override fun cameraToCreateCameraNote(picturePath: String) {
		val action = CameraFragmentDirections.actionCameraFragmentToCreateCameraNoteFragment(picturePath)
		navController.navigate(action)
	}

	override fun backToTabs() {
		navController.popBackStack(R.id.tabsFragment, false)
	}

	override fun toPermission(permission: Int) {
		val action = PermissionFragmentDirections.actionToPermissionFragment(permission)
		navController.navigate(action)
	}
}