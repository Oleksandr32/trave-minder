package com.oleksandrlysun.traveminder.presentation.screens.camera

import android.graphics.Matrix
import android.os.Bundle
import android.util.Rational
import android.util.Size
import android.view.*
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.screens.permission.PermissionResultLiveEvent
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CameraFragment : Fragment(), CameraView {

	@Inject
	lateinit var presenter: CameraPresenter

	@Inject
	lateinit var permissionResultLiveEvent: PermissionResultLiveEvent

	private val viewFinder by lazy { view!!.findViewById<TextureView>(R.id.view_finder) }

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_camera, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		AndroidSupportInjection.inject(this)
		super.onViewCreated(view, savedInstanceState)
		permissionResultLiveEvent.observe(this, Observer { presenter.onCameraPermissionDenied() })
		viewFinder.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ -> updateTransform() }
	}

	override fun onDestroyView() {
		permissionResultLiveEvent.removeObservers(this)
		CameraX.unbindAll()
		super.onDestroyView()
	}

	override fun startCamera() {
		viewFinder.post {
			val previewConfig = PreviewConfig.Builder()
					.setTargetAspectRatio(Rational(1, 1))
					.setTargetResolution(Size(640, 640))
					.build()

			val preview = Preview(previewConfig)
			preview.setOnPreviewOutputUpdateListener {
				val parent = viewFinder.parent as ViewGroup
				parent.removeView(viewFinder)
				parent.addView(viewFinder, 0)

				viewFinder.surfaceTexture = it.surfaceTexture
				updateTransform()
			}

			CameraX.bindToLifecycle(this, preview)
		}
	}

	private fun updateTransform() {
		val matrix = Matrix()
		val centerX = viewFinder.width / 2f
		val centerY = viewFinder.height / 2f

		val rotationDegrees = when (viewFinder.display.rotation) {
			Surface.ROTATION_0 -> 0
			Surface.ROTATION_90 -> 90
			Surface.ROTATION_180 -> 180
			Surface.ROTATION_270 -> 270
			else -> return
		}
		matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

		viewFinder.setTransform(matrix)
	}
}