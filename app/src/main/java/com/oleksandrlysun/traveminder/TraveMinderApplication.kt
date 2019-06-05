package com.oleksandrlysun.traveminder

import android.app.Activity
import android.app.Application
import android.content.BroadcastReceiver
import com.oleksandrlysun.traveminder.di.ApplicationModule
import com.oleksandrlysun.traveminder.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasBroadcastReceiverInjector
import javax.inject.Inject

class TraveMinderApplication : Application(), HasActivityInjector, HasBroadcastReceiverInjector {

	@Inject
	lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

	@Inject
	lateinit var dispatchingBroadcastReceiverInjector: DispatchingAndroidInjector<BroadcastReceiver>

	override fun onCreate() {
		super.onCreate()
		DaggerApplicationComponent.builder()
				.applicationModule(ApplicationModule(this))
				.build()
				.inject(this)
	}

	override fun activityInjector(): AndroidInjector<Activity> {
		return dispatchingActivityInjector
	}

	override fun broadcastReceiverInjector(): AndroidInjector<BroadcastReceiver> {
		return dispatchingBroadcastReceiverInjector
	}
}