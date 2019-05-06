package com.oleksandrlysun.traveminder

import android.app.Activity
import android.app.Application
import com.oleksandrlysun.traveminder.presentation.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TraveMinderApplication : Application(), HasActivityInjector {

	@Inject
	lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

	override fun onCreate() {
		super.onCreate()
		DaggerApplicationComponent.create().inject(this)
	}

	override fun activityInjector(): AndroidInjector<Activity> {
		return dispatchingActivityInjector
	}
}