package com.oleksandrlysun.traveminder.di

import com.oleksandrlysun.traveminder.TraveMinderApplication
import com.oleksandrlysun.traveminder.presentation.di.ActivityBindingModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
	ApplicationModule::class,
	ActivityBindingModule::class,
	AndroidSupportInjectionModule::class])
interface ApplicationComponent : AndroidInjector<TraveMinderApplication>