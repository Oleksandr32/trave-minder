package com.oleksandrlysun.traveminder.presentation.screens.camera

import android.content.Context
import android.graphics.Matrix
import android.os.Bundle
import android.util.Rational
import android.util.Size
import android.view.*
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.camera.core.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.extensions.*
import com.oleksandrlysun.traveminder.presentation.screens.permission.PermissionResultLiveEvent
import com.oleksandrlysun.traveminder.utils.lazy.ResettableLazyManager
import com.oleksandrlysun.traveminder.utils.resettableLazy
import dagger.android.support.AndroidSupportInjection
import java.io.File
import javax.inject.Inject

class CameraFragment : Fragment(), CameraView {

	@Inject
	lateinit var presenter: CameraPresenter

	@Inject
	lateinit var permissionResultLiveEvent: PermissionResultLiveEvent

	private val resettableLazyManager = ResettableLazyManager()
	private var imageCapture: ImageCapture? = null

	private lateinit var viewFinder: TextureView
	private val photoPreviewImageView by resettableLazy(resettableLazyManager) { view!!.findViewById<ImageView>(R.id.image_photo_preview) }
	private val captureButton by resettableLazy(resettableLazyManager) { view!!.findViewById<FloatingActionButton>(R.id.fab_capture) }
	private val applyButton by resettableLazy(resettableLazyManager) { view!!.findViewById<ImageButton>(R.id.btn_apply) }
	private val cancelButton by resettableLazy(resettableLazyManager) { view!!.findViewById<ImageButton>(R.id.btn_cancel) }
	private val loading by resettableLazy(resettableLazyManager) { view!!.findViewById<LinearLayout>(R.id.loading) }

	override fun onAttach(context: Context) {
		AndroidSupportInjection.inject(this)
		super.onAttach(context)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		permissionResultLiveEvent.observe(this, Observer { presenter.onCameraPermissionDenied() })
		setFullscreen(true)
		return inflater.inflate(R.layout.fragment_camera, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewFinder = view.findViewById(R.id.view_finder)
		presenter.onViewCreated()
		setupBackPressListener(view)
		setupViews()
	}

	override fun onDestroyView() {
		CameraX.unbindAll()
		permissionResultLiveEvent.removeObservers(this)
		resettableLazyManager.reset()
		setFullscreen(false)
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

			val imageCaptureConfig = ImageCaptureConfig.Builder()
					.setTargetAspectRatio(Rational(1, 1))
					.setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
					.build()
			imageCapture = ImageCapture(imageCaptureConfig)

			CameraX.bindToLifecycle(this, preview, imageCapture)
		}
	}

	override fun setCaptureButtonEnabled(enabled: Boolean) {
		captureButton.isClickable = enabled
		captureButton.isFocusable = enabled
		captureButton.animate().alpha(if (enabled) 1f else 0.5f).start()
	}

	override fun setLoading(loading: Boolean) {
		this.loading.visibleOrGone = loading
	}

	override fun takePicture() {
		val file = File(activity?.externalMediaDirs?.first(), "${System.currentTimeMillis()}.jpg")
		imageCapture?.takePicture(file) { imageBytes -> presenter.onPictureTaken(imageBytes) }
	}

	override fun showPicture(imageBytes: ByteArray) {
		photoPreviewImageView.setImageBitmap(imageBytes.toBitmap().rotate(90f))
	}

	override fun showConfirmPhotoView() {
		applyButton.showAndAnimate(R.animator.camera_photo_apply_show).start()
		cancelButton.showAndAnimate(R.animator.camera_photo_cancel_show).start()
		photoPreviewImageView.showAndAnimate(R.animator.fade_in).start()
	}

	override fun hideConfirmPhotoView() {
		applyButton.animateAndHide(R.animator.camera_photo_apply_hide).start()
		cancelButton.animateAndHide(R.animator.camera_photo_cancel_hide).start()
		photoPreviewImageView.animateAndHide(R.animator.fade_out).start()
	}

	private fun setupBackPressListener(view: View) {
		view.isFocusableInTouchMode = true
		view.requestFocus()
		view.setOnKeyListener { _, keyCode, event ->
			val shouldCancelPicture = keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP && photoPreviewImageView.visibleOrGone
			if (shouldCancelPicture) {
				presenter.cancelPicture()
				return@setOnKeyListener true
			}
			return@setOnKeyListener false
		}
	}

	private fun setupViews() {
		viewFinder.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ -> updateTransform() }
		captureButton.setOnClickListener { presenter.capturePicture() }
		applyButton.setOnClickListener { presenter.applyPicture() }
		cancelButton.setOnClickListener { presenter.cancelPicture() }
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

	private fun ImageCapture?.takePicture(file: File?, onCaptureSuccess: (ByteArray) -> Unit) {
		this?.takePicture(file, object : ImageCapture.OnImageSavedListener {
			override fun onImageSaved(file: File) {
				onCaptureSuccess(file.readBytes())
			}

			override fun onError(useCaseError: ImageCapture.UseCaseError, message: String, cause: Throwable?) {
				showToast(R.string.camera_capture_failed)
				cause?.printStackTrace()
			}
		})
	}
}