package com.oleksandrlysun.traveminder.presentation.extensions

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

inline fun <reified T> Fragment.findFragmentByType(): T? {
	return childFragmentManager.fragments.find { it is T } as T?
}

fun Fragment.showToast(@StringRes messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
	Toast.makeText(requireContext(), messageResId, duration).show()
}