package com.oleksandrlysun.traveminder.presentation.screens.camera

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.dialogs.DatePickerFragment
import com.oleksandrlysun.traveminder.presentation.dialogs.TimePickerFragment
import com.oleksandrlysun.traveminder.presentation.extensions.*
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import dagger.android.support.AndroidSupportInjection
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

class CreateCameraNoteFragment : Fragment(), CreateCameraNoteView {

	@Inject
	lateinit var presenter: CreateCameraNotePresenter

	private val picturePreview by lazy { view!!.findViewById<ImageView>(R.id.picture_preview) }
	private val titleContainer by lazy { view!!.findViewById<TextInputLayout>(R.id.container_title) }
	private val titleEditText by lazy { view!!.findViewById<TextInputEditText>(R.id.et_title) }
	private val addDateButton by lazy { view!!.findViewById<RelativeLayout>(R.id.btn_add_date) }
	private val dateContainer by lazy { view!!.findViewById<TextInputLayout>(R.id.container_date) }
	private val dateInput by lazy { view!!.findViewById<TextInputEditText>(R.id.et_date) }
	private val addAlarmDateButton by lazy { view!!.findViewById<RelativeLayout>(R.id.btn_add_alarm_date) }
	private val alarmContainer by lazy { view!!.findViewById<TextInputLayout>(R.id.container_alarm) }
	private val alarmDateInput by lazy { view!!.findViewById<TextInputEditText>(R.id.et_alarm_date) }
	private val saveButton by lazy { view!!.findViewById<FrameLayout>(R.id.btn_save) }

	override fun onAttach(context: Context) {
		AndroidSupportInjection.inject(this)
		super.onAttach(context)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_create_camera_note, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupViews()
		presenter.onViewCreated()
	}

	private fun setupViews() {
		mainActivity?.setSupportActionBar(view?.findViewById(R.id.toolbar))
		mainActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

		addDateButton.setOnClickListener { presenter.addField(NoteField.DATE) }
		addAlarmDateButton.setOnClickListener { presenter.addField(NoteField.ALARM_DATE) }
		dateInput.setOnClickListener { presenter.onDateInputClick() }
		alarmDateInput.setOnClickListener { presenter.onNotificationAlarmClick() }
		saveButton.setOnClickListener { presenter.saveNote(titleEditText.text.toString()) }
	}

	override fun setPicture(picture: ByteArray?) {
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
}