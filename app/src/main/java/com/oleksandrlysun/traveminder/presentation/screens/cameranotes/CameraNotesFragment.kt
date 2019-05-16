package com.oleksandrlysun.traveminder.presentation.screens.cameranotes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CameraNotesFragment : Fragment() {

	@Inject
	lateinit var navigation: MainNavigation

	override fun onAttach(context: Context) {
		AndroidSupportInjection.inject(this)
		super.onAttach(context)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_camera_notes, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		view.findViewById<FloatingActionButton>(R.id.fab_add_camera_note)?.setOnClickListener { navigation.tabsToCamera() }
	}
}