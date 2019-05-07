package com.oleksandrlysun.traveminder.presentation.screens.main

import android.content.Context
import com.oleksandrlysun.traveminder.R

class FeatureItemProvider(private val context: Context) {

	fun provide(): List<FeatureItem> {
		val titles = context.resources.obtainTypedArray(R.array.app_features_titles)
		val descriptions = context.resources.obtainTypedArray(R.array.app_features_descriptions)
		val pics = context.resources.obtainTypedArray(R.array.app_features_pics)
		val featureItems = (0 until titles.length()).map { index ->
			FeatureItem(titles.getString(index), descriptions.getString(index), pics.getDrawable(index))
		}
		titles.recycle()
		descriptions.recycle()
		pics.recycle()

		return featureItems
	}
}