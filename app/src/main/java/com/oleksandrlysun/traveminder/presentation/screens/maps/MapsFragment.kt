package com.oleksandrlysun.traveminder.presentation.screens.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.oleksandrlysun.traveminder.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng


class MapsFragment : Fragment(), OnMapReadyCallback {

	private lateinit var map: GoogleMap

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		// findFragmentByType<SupportMapFragment>()?.getMapAsync(this)
		return inflater.inflate(R.layout.fragment_maps, container, false)
	}

	override fun onMapReady(googleMap: GoogleMap) {
		map = googleMap

		// Add a marker in Sydney, Australia, and move the camera.
		val sydney = LatLng(-34.0, 151.0)
		map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
		map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
	}
}