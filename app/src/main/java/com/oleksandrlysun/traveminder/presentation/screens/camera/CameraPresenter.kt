package com.oleksandrlysun.traveminder.presentation.screens.camera

import com.oleksandrlysun.traveminder.presentation.di.scope.FragmentScope
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import com.oleksandrlysun.traveminder.utils.PermissionManager
import javax.inject.Inject

@FragmentScope
class CameraPresenter @Inject constructor(
		private val view: CameraView,
		private val state: CameraFlowState,
		private val navigation: MainNavigation,
		private val permissionManager: PermissionManager
) {

	fun onViewCreated() {
		if (permissionManager.isCameraPermissionGranted()) {
			view.startCamera()
		} else {
			navigation.toPermission()
		}
	}

	fun onCameraPermissionDenied() {
		navigation.navigateUp()
	}

	fun capturePicture() {
		view.takePicture()
		view.setCaptureButtonEnabled(false)
		view.setLoading(true)
	}

	fun applyPicture() {
		navigation.cameraToCreateCameraNote()
	}

	fun cancelPicture() {
		view.hideConfirmPhotoView()
		view.setCaptureButtonEnabled(true)
	}

	fun onPictureTaken(imageBytes: ByteArray) {
		state.picture = imageBytes
		view.setLoading(false)
		view.showConfirmPhotoView()
		view.showPicture(imageBytes)
	}
}