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

	private var picture: File? = null

	fun onViewCreated() {
		clearPicture()
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
		navigation.cameraToCreateCameraNote(picture!!.absolutePath)
	}

	fun cancelPicture() {
		clearPicture()
		view.hideConfirmPhotoView()
		view.setCaptureButtonEnabled(true)
	}

	fun onPictureTaken(picture: File) {
		this.picture = picture
		view.setLoading(false)
		view.showConfirmPhotoView()
		view.showPicture(picture)
	}

	private fun clearPicture() {
		picture?.delete()
		picture = null
	}
}