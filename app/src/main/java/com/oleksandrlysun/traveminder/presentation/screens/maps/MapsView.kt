package com.oleksandrlysun.traveminder.presentation.screens.maps

import com.oleksandrlysun.traveminder.domain.models.Location
import com.oleksandrlysun.traveminder.presentation.base.BaseView

interface MapsView : BaseView {

	fun updateTrack(location: Location)
}