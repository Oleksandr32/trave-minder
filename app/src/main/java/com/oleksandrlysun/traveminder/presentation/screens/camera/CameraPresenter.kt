package com.oleksandrlysun.traveminder.presentation.screens.camera

import com.oleksandrlysun.traveminder.presentation.di.scope.FragmentScope
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import com.oleksandrlysun.traveminder.utils.PermissionManager
import javax.inject.Inject

@FragmentScope
class CameraPresenter @Inject constructor(
		private val view: CameraView,
		private val navigation: MainNavigation,
		private val permissionManager: PermissionManager
) {

	init {
		checkCameraPermission()
	}

	fun onCameraPermissionDenied() {
		navigation.navigateUp()
	}

	private fun checkCameraPermission() {
		if (permissionManager.isCameraPermissionGranted()) {
			view.startCamera()
		} else {
			navigation.toPermission()
		}
	}
}