package com.oleksandrlysun.traveminder.presentation.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
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