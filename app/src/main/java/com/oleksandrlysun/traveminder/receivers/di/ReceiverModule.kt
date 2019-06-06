package com.oleksandrlysun.traveminder.receivers.di

import com.oleksandrlysun.traveminder.receivers.notification.NotificationPublisher
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ReceiverModule {

	@ContributesAndroidInjector
	fun contributeNotificationPublisherInjector(): NotificationPublisher
}