package com.oleksandrlysun.traveminder.presentation.screens.permission

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.extensions.mainActivity
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import com.oleksandrlysun.traveminder.presentation.screens.MainActivity
import com.oleksandrlysun.traveminder.utils.PermissionManager
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PermissionFragment : Fragment() {

	@Inject
	lateinit var navigation: MainNavigation

	@Inject
	lateinit var permissionManager: PermissionManager

	@Inject
	lateinit var permissionResultLiveEvent: PermissionResultLiveEvent

	private val args: PermissionFragmentArgs by navArgs()

	override fun onAttach(context: Context) {
		AndroidSupportInjection.inject(this)
		super.onAttach(context)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_permission, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		view.findViewById<TextView>(R.id.btn_deny).setOnClickListener { setRequestResult(false) }
		view.findViewById<TextView>(R.id.btn_allow)
				.setOnClickListener { permissionManager.requestCameraPermission(RC_CAMERA_PERMISSION) }

		mainActivity?.setPermissionListener { requestCode, _, grantResults ->
			val granted = permissionManager.checkPermissionsResult(requestCode, RC_CAMERA_PERMISSION, grantResults)
			setRequestResult(granted)
		}
	}

	private fun setRequestResult(granted: Boolean) {
		if (!granted) {
			permissionResultLiveEvent.call()
		}
		navigation.navigateUp()
	}

	companion object {

		private const val RC_CAMERA_PERMISSION = 0
		private const val RC_LOCATION_PERMISSION = 1
	}
}