package com.oleksandrlysun.traveminder.presentation.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import java.text.SimpleDateFormat
import java.util.Date

fun ByteArray.toBitmap(): Bitmap {
	return BitmapFactory.decodeByteArray(this, 0, size)
}

fun Bitmap.rotate(degrees: Float): Bitmap {
	val transformMatrix = Matrix()
	transformMatrix.postRotate(degrees)
	return Bitmap.createBitmap(this, 0, 0, width, height, transformMatrix, false)
}

fun Date.toFormat(format: String = "dd MMMM yyyy"): String {
	return SimpleDateFormat(format).format(this)
}