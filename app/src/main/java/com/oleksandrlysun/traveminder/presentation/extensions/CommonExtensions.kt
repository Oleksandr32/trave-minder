package com.oleksandrlysun.traveminder.presentation.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import com.google.android.gms.maps.model.LatLng
import com.oleksandrlysun.traveminder.domain.models.Location
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

fun File.toBitmap(): Bitmap {
	val imageBytes = readBytes()
	return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}

fun Bitmap.rotate(degrees: Float): Bitmap {
	val transformMatrix = Matrix()
	transformMatrix.postRotate(degrees)
	return Bitmap.createBitmap(this, 0, 0, width, height, transformMatrix, false)
}

fun Date.toFormat(format: String = "dd MMMM yyyy"): String {
	return SimpleDateFormat(format).format(this)
}

fun String.ellipse(limit: Int = 15): String {
	return if (length >= limit) "${substring(0, limit)}..." else this
}

fun String?.contains(query: String): Boolean {
	return this?.contains(query, ignoreCase = true) ?: false
}

fun Location.toLatLng() = LatLng(latitude, longitude)