package com.oleksandrlysun.traveminder.presentation.screens.camera

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.extensions.mainActivity
import com.oleksandrlysun.traveminder.presentation.extensions.rotate
import com.oleksandrlysun.traveminder.presentation.extensions.toBitmap
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CreateCameraNoteFragment : Fragment() {

	@Inject
	lateinit var state: CameraFlowState

	private val picturePreview by lazy { view!!.findViewById<ImageView>(R.id.picture_preview) }

	override fun onAttach(context: Context) {
		AndroidSupportInjection.inject(this)
		super.onAttach(context)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_create_camera_note, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupToolbar()
		setState()
	}

	private fun setupToolbar() {
		mainActivity?.setSupportActionBar(view?.findViewById(R.id.toolbar))
		mainActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
	}

	private fun setState() {
		picturePreview.setImageBitmap(state.picture?.toBitmap()?.rotate(90f))
	}
}