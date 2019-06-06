package com.oleksandrlysun.traveminder.utils.notification

import com.oleksandrlysun.traveminder.receivers.notification.NotificationRequest

interface NotificationUtils {

	fun showNotification(request: NotificationRequest)

	fun scheduleNotification(request: NotificationRequest)
}