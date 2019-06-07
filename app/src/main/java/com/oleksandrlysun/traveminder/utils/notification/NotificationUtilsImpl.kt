package com.oleksandrlysun.traveminder.utils.notification

import android.annotation.TargetApi
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.receivers.notification.NotificationPublisher
import com.oleksandrlysun.traveminder.receivers.notification.NotificationRequest
import com.oleksandrlysun.traveminder.utils.ParcelableUtils
import javax.inject.Inject

class NotificationUtilsImpl @Inject constructor(private val context: Context) : NotificationUtils {

	private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

	init {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			createNotificationChannel()
		}
	}

	override fun showNotification(request: NotificationRequest) {
		showNotification(request.id, buildNotification((request)))
	}

	override fun scheduleNotification(request: NotificationRequest) {
		val notificationIntent = Intent(context, NotificationPublisher::class.java).apply {
			putExtra(NotificationPublisher.NOTIFICATION, ParcelableUtils.marshall(request))
			action = NotificationPublisher.NOTIFICATION_ACTION
		}

		val pendingIntent = PendingIntent.getBroadcast(context,
				request.id,
				notificationIntent,
				PendingIntent.FLAG_UPDATE_CURRENT)

		val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
		alarmManager.set(AlarmManager.RTC_WAKEUP, request.triggerAtMillis, pendingIntent)
	}

	private fun showNotification(notificationId: Int, notification: Notification) {
		notificationManager.notify(notificationId, notification)
	}

	private fun buildNotification(request: NotificationRequest): Notification {
		return NotificationCompat.Builder(context, CHANNEL_ID)
				.setSmallIcon(R.drawable.travel)
				.setContentTitle(request.title)
				.setContentText(request.content)
				.setDefaults(NotificationCompat.DEFAULT_ALL)
				.setPriority(NotificationCompat.PRIORITY_HIGH)
				.setStyle(NotificationCompat.BigTextStyle())
				.build()
	}

	@TargetApi(Build.VERSION_CODES.O)
	private fun createNotificationChannel() {
		val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
		notificationManager.createNotificationChannel(channel)
	}

	companion object {
		private const val CHANNEL_ID = "com.oleksandrlysun.traveminder.NOTIFICATION_CHANNEL_ID"
		private const val CHANNEL_NAME = "TRAVEMINDER_NOTIFICATION_CHANNEL"
	}
}