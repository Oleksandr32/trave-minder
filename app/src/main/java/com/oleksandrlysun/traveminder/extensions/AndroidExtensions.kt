package com.oleksandrlysun.traveminder.extensions

import androidx.appcompat.app.AppCompatActivity

inline fun <reified T> AppCompatActivity.findFragmentByType(): T? {
	return supportFragmentManager.fragments.find { it is T } as T?
}