package com.oleksandrlysun.traveminder.presentation.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

	@Inject
	lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

	@Inject
	lateinit var navigation: MainNavigation

	private var permissionListener: ((Int, Array<out String>, IntArray) -> Unit)? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		AndroidInjection.inject(this)
	}

	override fun supportFragmentInjector(): AndroidInjector<Fragment> {
		return fragmentInjector
	}

	override fun onSupportNavigateUp(): Boolean {
		return navigation.navigateUp()
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		permissionListener?.invoke(requestCode, permissions, grantResults)
	}

	fun setPermissionListener(listener: (Int, Array<out String>, IntArray) -> Unit) {
		permissionListener = listener
	}
}