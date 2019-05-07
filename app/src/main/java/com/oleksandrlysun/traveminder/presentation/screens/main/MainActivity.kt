package com.oleksandrlysun.traveminder.presentation.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.oleksandrlysun.traveminder.R

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		findViewById<ViewPager>(R.id.pager).adapter = FeaturesAdapter(FeatureItemProvider(this).provide())
		findViewById<TextView>(R.id.btn_continue).setOnClickListener {
		}
	}
}