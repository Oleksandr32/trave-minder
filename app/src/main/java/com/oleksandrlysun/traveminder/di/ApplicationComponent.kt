package com.oleksandrlysun.traveminder.di

import com.oleksandrlysun.traveminder.TraveMinderApplication
import com.oleksandrlysun.traveminder.presentation.di.ActivityBindingModule
import com.oleksandrlysun.traveminder.services.di.ServiceModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
	ApplicationModule::class,
	ActivityBindingModule::class,
	ServiceModule::class,
	UtilModule::class,
	AndroidSupportInjectionModule::class])
interface ApplicationComponent : AndroidInjector<TraveMinderApplication>