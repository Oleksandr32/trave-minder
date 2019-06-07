package com.oleksandrlysun.traveminder.presentation.extensions

import android.app.Activity
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.oleksandrlysun.traveminder.presentation.screens.MainActivity
import java.io.File

val Fragment.mainActivity: MainActivity?
	get() = activity as? MainActivity

inline fun <reified T> Fragment.findFragmentByType(): T? {
	return childFragmentManager.fragments.find { it is T } as T?
}

fun Fragment.showToast(@StringRes messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
	Toast.makeText(requireContext(), messageResId, duration).show()
}

fun Fragment.setFullscreen(isFullscreen: Boolean) {
	activity?.window?.run { if (isFullscreen) addFlags(FLAG_FULLSCREEN) else clearFlags(FLAG_FULLSCREEN) }
}

fun Activity.createFile(name: String = "${System.currentTimeMillis()}.jpg"): File {
	return File(externalMediaDirs.first(), name)
}