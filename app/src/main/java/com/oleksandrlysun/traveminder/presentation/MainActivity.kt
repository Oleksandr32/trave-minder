package com.oleksandrlysun.traveminder.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.extensions.startActivity
import com.oleksandrlysun.traveminder.presentation.screens.MapsActivity

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}
}
