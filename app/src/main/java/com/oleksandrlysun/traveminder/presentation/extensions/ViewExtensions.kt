package com.oleksandrlysun.traveminder.presentation.extensions

import android.animation.Animator
import android.animation.AnimatorInflater
import android.content.Context
import android.view.View
import androidx.annotation.AnimatorRes
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import android.view.inputmethod.InputMethodManager


/**
 * Animates view with animator from resources
 */

fun View.animate(@AnimatorRes animatorResId: Int): Animator {
	val animator = AnimatorInflater.loadAnimator(context, animatorResId)
	animator.setTarget(this)
	return animator
}

/**
 * Animates view with animator from resources and shows it on animation start
 */
fun View.showAndAnimate(@AnimatorRes animatorResId: Int): Animator {
	val animator = animate(animatorResId)
	animator.doOnStart { visibility = View.VISIBLE }
	return animator
}

/**
 * Animates view with animator from resources and sets its visibility to GONE on animation end
 */
fun View.animateAndHide(@AnimatorRes animatorResId: Int): Animator {
	val animator = animate(animatorResId)
	animator.doOnEnd { visibility = View.GONE }
	return animator
}

var View.visibleOrGone: Boolean
	set(value) { visibility = if (value) View.VISIBLE else View.GONE }
	get() = visibility == View.VISIBLE

fun View.showSoftInput() {
	requestFocus()
	val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
	imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideSoftInput() {
	val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
	imm.hideSoftInputFromWindow(windowToken, 0)
}