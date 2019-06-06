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
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class TraveMinderApplication : Application(), HasActivityInjector, HasBroadcastReceiverInjector {

	@Inject
	lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

	@Inject
	lateinit var dispatchingBroadcastReceiverInjector: DispatchingAndroidInjector<BroadcastReceiver>

	override fun onCreate() {
		super.onCreate()
		initDagger()
		initRealm()
	}

	private fun initDagger() {
		DaggerApplicationComponent.builder()
				.applicationModule(ApplicationModule(this))
				.build()
				.inject(this)
	}

	private fun initRealm() {
		Realm.init(this)

		val config = RealmConfiguration.Builder()
				.deleteRealmIfMigrationNeeded()
				.build()
		Realm.setDefaultConfiguration(config)
	}

	override fun activityInjector(): AndroidInjector<Activity> {
		return dispatchingActivityInjector
	}

	override fun broadcastReceiverInjector(): AndroidInjector<BroadcastReceiver> {
		return dispatchingBroadcastReceiverInjector
	}
}