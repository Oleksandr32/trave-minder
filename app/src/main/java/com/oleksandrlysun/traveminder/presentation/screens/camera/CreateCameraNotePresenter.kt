package com.oleksandrlysun.traveminder.presentation.screens.camera

import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.di.scope.FragmentScope
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import java.util.Date
import javax.inject.Inject

@FragmentScope
class CreateCameraNotePresenter @Inject constructor(private val view: CreateCameraNoteView,
                                                    private val state: CameraFlowState,
                                                    private val navigation: MainNavigation) {

	private var date: Date? = null
	private var alarmDate: Date? = null

	fun onViewCreated() {
		view.setPicture(state.picture)
	}

	fun addField(field: NoteField) {
		view.setFieldVisibility(field, true)
	}

	fun onDateInputClick() {
		view.showDatePicker(date)
	}

	fun onNotificationAlarmClick() {
		view.showTimePicker(alarmDate)
	}

	fun onDatePicked(date: Date) {
		this.date = date
		view.setDate(date)
	}

	fun onTimePicked(date: Date) {
		alarmDate = date
		view.setAlarmDate(date)
	}

	fun saveNote(title: String) {
		if (title.isEmpty()) {
			view.setTitleError(R.string.title_error)
		} else {
			navigation.backToTabs()
		}
	}
}