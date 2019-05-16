package com.oleksandrlysun.traveminder.presentation.navigation

interface MainNavigation {

	fun navigateUp(): Boolean

	fun welcomeToTabs()

	fun tabsToMaps()

	fun tabsToCamera()

	fun toPermission()
}