package com.oleksandrlysun.traveminder.presentation.extensions

import android.animation.Animator
import android.animation.AnimatorInflater
import android.view.View
import androidx.annotation.AnimatorRes
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart


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
