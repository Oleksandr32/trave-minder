package com.oleksandrlysun.traveminder.presentation.screens.camera

import com.oleksandrlysun.traveminder.presentation.di.scope.FragmentScope
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import com.oleksandrlysun.traveminder.utils.PermissionManager
import java.io.File
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

	fun capturePicture() {
		view.setCaptureButtonEnabled(false)
		view.takePicture()
	}

	fun applyPicture() {
	}

	fun cancelPicture() {
	}

	fun onPictureTaken(imageBytes: ByteArray) {
		view.showPicture(imageBytes)
		view.showConfirmPhotoView()
	}

	private fun checkCameraPermission() {
		if (permissionManager.isCameraPermissionGranted()) {
			view.startCamera()
		} else {
			navigation.toPermission()
		}
	}
}