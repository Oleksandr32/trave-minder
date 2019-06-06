package com.oleksandrlysun.traveminder.di

import com.oleksandrlysun.traveminder.TraveMinderApplication
import com.oleksandrlysun.traveminder.data.di.DataModule
import com.oleksandrlysun.traveminder.domain.di.DomainModule
import com.oleksandrlysun.traveminder.presentation.di.ActivityBindingModule
import com.oleksandrlysun.traveminder.receivers.di.ReceiverModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
	ApplicationModule::class,
	DataModule::class,
	DomainModule::class,
	ActivityBindingModule::class,
	ReceiverModule::class,
	UtilModule::class,
	AndroidSupportInjectionModule::class])
interface ApplicationComponent : AndroidInjector<TraveMinderApplication>