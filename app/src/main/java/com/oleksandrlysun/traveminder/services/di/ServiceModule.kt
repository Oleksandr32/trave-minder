package com.oleksandrlysun.traveminder.services.di

import com.oleksandrlysun.traveminder.services.notification.NotificationPublisher
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ServiceModule {

	@ContributesAndroidInjector
	fun contributeNotificationPublisherInjector(): NotificationPublisher
}