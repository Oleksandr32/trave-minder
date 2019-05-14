package com.oleksandrlysun.traveminder.presentation.extensions

import androidx.fragment.app.Fragment

inline fun <reified T> Fragment.findFragmentByType(): T? {
	return childFragmentManager.fragments.find { it is T } as T?
}