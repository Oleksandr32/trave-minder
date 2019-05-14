package com.oleksandrlysun.traveminder.presentation.screens.welcome

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class WelcomeFragment : Fragment() {

	@Inject
	lateinit var navigation: MainNavigation

	@Inject
	lateinit var featuresProvider: FeatureItemProvider

	override fun onAttach(context: Context) {
		AndroidSupportInjection.inject(this)
		super.onAttach(context)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_welcome, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		view.findViewById<ViewPager>(R.id.pager).adapter = FeaturesAdapter(featuresProvider.provide())
		view.findViewById<TextView>(R.id.btn_continue).setOnClickListener { navigation.welcomeToTabs() }
	}
}