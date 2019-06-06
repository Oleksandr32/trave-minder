package com.oleksandrlysun.traveminder.receivers.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.oleksandrlysun.traveminder.utils.ParcelableUtils
import com.oleksandrlysun.traveminder.utils.notification.NotificationUtilsImpl
import dagger.android.AndroidInjection
import javax.inject.Inject

class NotificationPublisher : BroadcastReceiver() {

	@Inject
	lateinit var notificationUtils: NotificationUtilsImpl

	override fun onReceive(context: Context, intent: Intent) {
		AndroidInjection.inject(this, context)
		val notificationRequest = ParcelableUtils.unmarshall(intent.getByteArrayExtra(NOTIFICATION), NotificationRequest.CREATOR)
		notificationRequest?.let {
			notificationUtils.showNotification(it)
		}
	}

	companion object {
		const val NOTIFICATION = "com.oleksandrlysun.traveminder.NOTIFICATION"
		const val NOTIFICATION_ACTION = "com.oleksandrlysun.traveminder.NOTIFICATION_ACTION"
	}
}