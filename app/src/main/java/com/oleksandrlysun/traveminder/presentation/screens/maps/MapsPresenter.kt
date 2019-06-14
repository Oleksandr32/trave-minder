package com.oleksandrlysun.traveminder.presentation.screens.maps

import com.oleksandrlysun.traveminder.presentation.base.Presenter
import com.oleksandrlysun.traveminder.presentation.di.scope.FragmentScope
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import com.oleksandrlysun.traveminder.presentation.screens.permission.LOCATION_PERMISSION
import com.oleksandrlysun.traveminder.utils.LocationServiceHelper
import com.oleksandrlysun.traveminder.utils.PermissionManager
import javax.inject.Inject

@FragmentScope
class MapsPresenter @Inject constructor(view: MapsView,
                                        private val navigation: MainNavigation,
                                        private val permissionManager: PermissionManager,
                                        private val locationServiceHelper: LocationServiceHelper)
	: Presenter<MapsView>(view) {

	fun checkPermissions() {
		if (!permissionManager.isLocationPermissionGranted()) {
			navigation.toPermission(LOCATION_PERMISSION)
		}
	}

	override fun onResume() {
		super.onResume()
		locationServiceHelper.startLocationUpdates { view.updateTrack(it) }

	}

	override fun onPause() {
		super.onPause()
		locationServiceHelper.stopLocationUpdates()
	}
}