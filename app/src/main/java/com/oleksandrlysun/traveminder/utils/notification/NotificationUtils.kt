package com.oleksandrlysun.traveminder.utils.notification

import com.oleksandrlysun.traveminder.services.notification.NotificationRequest

interface NotificationUtils {

	fun showNotification(request: NotificationRequest)

	fun scheduleNotification(request: NotificationRequest)
}