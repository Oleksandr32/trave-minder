package com.oleksandrlysun.traveminder.utils

import android.content.Context
import com.oleksandrlysun.traveminder.domain.models.Location
import io.nlopez.smartlocation.SmartLocation
import javax.inject.Inject

class LocationServiceHelper @Inject constructor(context: Context) {

	private val smartLocation = SmartLocation.with(context)

	fun startLocationUpdates(listener: (Location) -> Unit) {
		smartLocation.location().start { listener(Location(it.latitude, it.longitude)) }
	}

	fun stopLocationUpdates() {
		smartLocation.location().stop()
	}
}