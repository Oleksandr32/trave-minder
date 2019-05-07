package com.oleksandrlysun.traveminder.presentation.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.oleksandrlysun.traveminder.databinding.ItemFeatureBinding

class FeaturesAdapter(private val items: List<FeatureItem>) : PagerAdapter() {

	override fun getCount() = items.size

	override fun getItemPosition(`object`: Any) = POSITION_NONE

	override fun isViewFromObject(view: View, `object`: Any): Boolean {
		return view == `object`
	}

	override fun instantiateItem(container: ViewGroup, position: Int): Any {
		val binding = ItemFeatureBinding.inflate(LayoutInflater.from(container.context), container, false)
		binding.feature = items[position]
		container.addView(binding.root)
		return binding.root
	}

	override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
		container.removeView(`object` as LinearLayout)
	}
}