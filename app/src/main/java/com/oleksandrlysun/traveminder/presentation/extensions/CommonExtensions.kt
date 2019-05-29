package com.oleksandrlysun.traveminder.presentation.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix

fun ByteArray.toBitmap(): Bitmap {
	return BitmapFactory.decodeByteArray(this, 0, size)
}

fun Bitmap.rotate(degrees: Float): Bitmap {
	val transformMatrix = Matrix()
	transformMatrix.postRotate(degrees)
	return Bitmap.createBitmap(this, 0, 0, width, height, transformMatrix, false)
}