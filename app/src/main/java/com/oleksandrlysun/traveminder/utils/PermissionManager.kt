package com.oleksandrlysun.traveminder.utils

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.oleksandrlysun.traveminder.presentation.screens.MainActivity
import javax.inject.Inject

class PermissionManager @Inject constructor(private val activity: MainActivity) {

	fun isCameraPermissionGranted(): Boolean {
		return ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PERMISSION_GRANTED
	}

	fun requestCameraPermission(requestCode: Int) {
		ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA), requestCode)
	}

	fun checkPermissionsResult(requestCode: Int, resultCode: Int, grantResults: IntArray): Boolean {
		return grantResults.isNotEmpty()
				&& grantResults[0] == PERMISSION_GRANTED
				&& requestCode == resultCode
	}

	fun isLocationPermissionGranted(): Boolean {
		return ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PERMISSION_GRANTED
	}

	fun requestLocationPermission(requestCode: Int) {
		ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), requestCode)
	}
}