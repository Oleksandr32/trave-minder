package com.oleksandrlysun.traveminder.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class PresenterFragment : Fragment(), BaseView {

	protected abstract val layoutResId: Int

	final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(layoutResId, container, false)
	}

	final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		AndroidSupportInjection.inject(this)
		setupUI()
		getPresenters().forEach { it.onViewCreated() }
	}

	override fun onDestroyView() {
		getPresenters().forEach { it.onDestroyView() }
		super.onDestroyView()
	}

	protected abstract fun getPresenters(): List<Presenter<out BaseView>>

	@CallSuper
	protected open fun setupUI() {
	}
}