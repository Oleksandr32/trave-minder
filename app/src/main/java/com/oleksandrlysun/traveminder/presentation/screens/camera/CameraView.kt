package com.oleksandrlysun.traveminder.presentation.screens.camera

import java.io.File

interface CameraView {

	fun startCamera()

	fun setCaptureButtonEnabled(enabled: Boolean)

	fun setLoading(loading: Boolean)

	fun takePicture()

	fun showPicture(picture: File)

	fun showConfirmPhotoView()

	fun hideConfirmPhotoView()
}