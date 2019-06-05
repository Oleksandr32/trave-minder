package com.oleksandrlysun.traveminder.di

import android.content.Context
import com.oleksandrlysun.traveminder.utils.notification.NotificationUtils
import com.oleksandrlysun.traveminder.utils.notification.NotificationUtilsImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilModule {

	@Singleton
	@Provides
	fun provideNotificationUtils(context: Context): NotificationUtils {
		return NotificationUtilsImpl(context)
	}
}