package com.oleksandrlysun.traveminder.presentation.dialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import java.util.Calendar
import java.util.Date

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

	private val calendar = Calendar.getInstance()
	private var listener: ((Date) -> Unit)? = null
	private var date: Date? = null

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		if (date != null) {
			calendar.time = date
		}
		val hour = calendar.get(Calendar.HOUR_OF_DAY)
		val minute = calendar.get(Calendar.MINUTE)

		return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
	}

	fun show(manager: FragmentManager, tag: String? = null, listener: (Date) -> Unit, date: Date? = null) {
		super.show(manager, tag)
		this.listener = listener
		this.date = date
	}

	override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
		with(calendar) {
			set(Calendar.HOUR_OF_DAY, hourOfDay)
			set(Calendar.MINUTE, minute)
		}
		listener?.invoke(calendar.time)
	}
}
