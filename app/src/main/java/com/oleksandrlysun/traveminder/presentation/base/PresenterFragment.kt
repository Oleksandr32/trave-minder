package com.oleksandrlysun.traveminder.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.extensions.mainActivity
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

	override fun onResume() {
		super.onResume()
		getPresenters().forEach { it.onResume() }
	}

	override fun onPause() {
		super.onPause()
		getPresenters().forEach { it.onPause() }
	}

	override fun onDestroyView() {
		getPresenters().forEach { it.onDestroyView() }
		super.onDestroyView()
	}

	protected abstract fun getPresenters(): List<Presenter<out BaseView>>

	@CallSuper
	protected open fun setupUI() {
		setupToolbar()
	}

	@CallSuper
	protected open fun setupToolbar() {
		mainActivity?.setSupportActionBar(view?.findViewById(R.id.toolbar))
	}
}