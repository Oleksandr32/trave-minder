package com.oleksandrlysun.traveminder.presentation.screens.camera

interface CameraView {

	fun startCamera()

	fun setCaptureButtonEnabled(enabled: Boolean)

	fun setLoading(loading: Boolean)

	fun takePicture()

	fun showPicture(imageBytes: ByteArray)

	fun showConfirmPhotoView()

	fun hideConfirmPhotoView()
}