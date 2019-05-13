package com.oleksandrlysun.traveminder.presentation.screens.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.navigation.TabsNavigation
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class TabsFragment : Fragment() {

	@Inject
	lateinit var navigation: TabsNavigation

	private val bottomNavigation by lazy { view?.findViewById<BottomNavigationView>(R.id.bottom_navigation) }

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_tabs, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		AndroidSupportInjection.inject(this)
		super.onViewCreated(view, savedInstanceState)
		bottomNavigation?.setupWithNavController(navigation.getNavController())
	}
}