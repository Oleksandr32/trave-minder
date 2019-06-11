package com.oleksandrlysun.traveminder.presentation.screens.camera

import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.domain.interactors.StorageInteractor
import com.oleksandrlysun.traveminder.domain.models.CameraNote
import com.oleksandrlysun.traveminder.domain.repositories.CameraNoteRepository
import com.oleksandrlysun.traveminder.presentation.base.Presenter
import com.oleksandrlysun.traveminder.presentation.di.scope.FragmentScope
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import com.oleksandrlysun.traveminder.receivers.notification.NotificationRequest
import com.oleksandrlysun.traveminder.utils.notification.NotificationUtils
import java.io.File
import java.util.Date
import javax.inject.Inject

@FragmentScope
class CreateCameraNotePresenter @Inject constructor(view: CreateCameraNoteView,
                                                    private val navigation: MainNavigation,
                                                    private val storageInteractor: StorageInteractor,
                                                    private val notificationUtils: NotificationUtils)
	: Presenter<CreateCameraNoteView>(view) {

	private var picture: File? = null
	private var date: Date? = null
	private var alarmDate: Date? = null

	fun consumeArgs(picturePath: String) {
		picture = File(picturePath)
		view.setPicture(picture)
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

	fun saveNote(title: String, tags: List<String>, description: String?) {
		if (title.isEmpty()) {
			view.setTitleError(R.string.title_error)
			return
		}

		launchWithHandler {
			try {
				val note = CameraNote(
						title = title,
						tags = tags.takeIf { it.isNotEmpty() },
						description = description,
						date = date,
						picturePath = picture!!.absolutePath)
				storageInteractor.add(note)

				alarmDate?.let {
					val request = NotificationRequest(System.currentTimeMillis().toInt(), title, description, it.time)
					notificationUtils.scheduleNotification(request)
				}
				navigation.backToTabs()
			} catch (throwable: Throwable) {
				throwable.printStackTrace()
			}
		}
	}
}