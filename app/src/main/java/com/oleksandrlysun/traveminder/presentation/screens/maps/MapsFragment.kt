package com.oleksandrlysun.traveminder.presentation.screens.maps

import android.annotation.SuppressLint
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import com.oleksandrlysun.traveminder.presentation.extensions.findFragmentByType
import com.google.android.gms.maps.model.Polyline
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.domain.models.Location
import com.oleksandrlysun.traveminder.presentation.base.PresenterFragment
import com.oleksandrlysun.traveminder.presentation.extensions.toLatLng
import javax.inject.Inject

class MapsFragment : PresenterFragment(), MapsView, OnMapReadyCallback {

	@Inject
	lateinit var presenter: MapsPresenter

	private lateinit var map: GoogleMap
	private val gpsTrack: Polyline? = null

	override val layoutResId = R.layout.fragment_maps

	override fun getPresenters() = listOf(presenter)

	override fun setupUI() {
		super.setupUI()
		findFragmentByType<SupportMapFragment>()?.getMapAsync(this)
	}

	@SuppressLint("MissingPermission")
	override fun onMapReady(googleMap: GoogleMap) {
		map = googleMap

		// Add a marker in Sydney, Australia, and move the camera.
		val sydney = LatLng(-34.0, 151.0)
		map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
		map.moveCamera(CameraUpdateFactory.newLatLng(sydney))

		presenter.checkPermissions()
	}

	override fun updateTrack(location: Location) {
		val points = gpsTrack?.points
		points?.add(location.toLatLng())
		gpsTrack?.points = points
	}
}