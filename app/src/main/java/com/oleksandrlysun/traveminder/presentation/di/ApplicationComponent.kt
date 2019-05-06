package com.oleksandrlysun.traveminder.presentation.di

import com.oleksandrlysun.traveminder.TraveMinderApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [AndroidInjectionModule::class])
interface ApplicationComponent : AndroidInjector<TraveMinderApplication>