package com.oleksandrlysun.traveminder.presentation.screens.camera

import androidx.annotation.StringRes
import java.util.Date

interface CreateCameraNoteView {

	fun setPicture(picture: ByteArray?)

	fun setFieldVisibility(field: NoteField, visible: Boolean)

	fun setDate(date: Date)

	fun setAlarmDate(date: Date)

	fun showDatePicker(date: Date?)

	fun showTimePicker(date: Date?)

	fun setTitleError(@StringRes errorResId: Int)
}