package com.oleksandrlysun.traveminder.presentation.screens.camera

import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.base.PresenterFragment
import com.oleksandrlysun.traveminder.presentation.dialogs.DatePickerFragment
import com.oleksandrlysun.traveminder.presentation.dialogs.TimePickerFragment
import com.oleksandrlysun.traveminder.presentation.extensions.*
import com.oleksandrlysun.traveminder.presentation.views.TagsView
import java.io.File
import java.util.Date
import javax.inject.Inject

class CreateCameraNoteFragment : PresenterFragment(), CreateCameraNoteView {

	@Inject
	lateinit var presenter: CreateCameraNotePresenter

	private val picturePreview by lazy { view!!.findViewById<ImageView>(R.id.picture_preview) }
	private val titleContainer by lazy { view!!.findViewById<TextInputLayout>(R.id.container_title) }
	private val titleEditText by lazy { view!!.findViewById<TextInputEditText>(R.id.et_title) }
	private val descriptionEditText by lazy { view!!.findViewById<TextInputEditText>(R.id.et_description) }
	private val tagsView by lazy { view!!.findViewById<TagsView>(R.id.tags_view) }
	private val addDateButton by lazy { view!!.findViewById<RelativeLayout>(R.id.btn_add_date) }
	private val dateContainer by lazy { view!!.findViewById<TextInputLayout>(R.id.container_date) }
	private val dateInput by lazy { view!!.findViewById<TextInputEditText>(R.id.et_date) }
	private val addAlarmDateButton by lazy { view!!.findViewById<RelativeLayout>(R.id.btn_add_alarm_date) }
	private val alarmContainer by lazy { view!!.findViewById<TextInputLayout>(R.id.container_alarm) }
	private val alarmDateInput by lazy { view!!.findViewById<TextInputEditText>(R.id.et_alarm_date) }
	private val saveButton by lazy { view!!.findViewById<FrameLayout>(R.id.btn_save) }

	private val args: CreateCameraNoteFragmentArgs by navArgs()

	override val layoutResId = R.layout.fragment_create_camera_note

	override fun getPresenters() = listOf(presenter)

	override fun setupUI() {
		super.setupUI()
		mainActivity?.setSupportActionBar(view?.findViewById(R.id.toolbar))
		mainActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

		addDateButton.setOnClickListener { presenter.addField(NoteField.DATE) }
		addAlarmDateButton.setOnClickListener { presenter.addField(NoteField.ALARM_DATE) }
		dateInput.setOnClickListener { presenter.onDateInputClick() }
		alarmDateInput.setOnClickListener { presenter.onNotificationAlarmClick() }
		saveButton.setOnClickListener { saveNote() }

		presenter.consumeArgs(args.picturePath)
	}

	override fun setPicture(picture: File?) {
		picturePreview.setImageBitmap(picture?.toBitmap()?.rotate(90f))
	}

	override fun setFieldVisibility(field: NoteField, visible: Boolean) {
		when (field) {
			NoteField.DATE -> {
				dateContainer.visibleOrGone = visible
				addDateButton.visibleOrGone = !visible
			}
			NoteField.ALARM_DATE -> {
				alarmContainer.visibleOrGone = visible
				addAlarmDateButton.visibleOrGone = !visible
			}
		}
	}

	override fun setDate(date: Date) {
		dateInput.setText(date.toFormat())
	}

	override fun setAlarmDate(date: Date) {
		alarmDateInput.setText(date.toFormat(format = getString(R.string.alarm_date_format)))
	}

	override fun showDatePicker(date: Date?) {
		DatePickerFragment().show(requireFragmentManager(), listener = presenter::onDatePicked, date = date)
	}

	override fun showTimePicker(date: Date?) {
		TimePickerFragment().show(requireFragmentManager(), listener = presenter::onTimePicked, date = date)
	}

	override fun setTitleError(errorResId: Int) {
		titleContainer.error = getString(errorResId)
	}

	private fun saveNote() {
		presenter.saveNote(titleEditText.text.toString(), tagsView.tagsName, descriptionEditText.text.toString())
	}
}