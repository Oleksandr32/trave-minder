package com.oleksandrlysun.traveminder.data.di

import com.oleksandrlysun.traveminder.data.repositories.di.RepositoryModule
import dagger.Module

@Module(includes = [RepositoryModule::class])
class DataModule