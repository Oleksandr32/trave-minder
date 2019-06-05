package com.oleksandrlysun.traveminder.presentation.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import java.util.Date
import java.util.Calendar

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

	private val calendar = Calendar.getInstance()
	private var listener: ((Date) -> Unit)? = null
	private var date: Date? = null

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		if (date != null) {
			calendar.time = date
		}
		val year = calendar.get(Calendar.YEAR)
		val month = calendar.get(Calendar.MONTH)
		val day = calendar.get(Calendar.DAY_OF_MONTH)

		return DatePickerDialog(requireContext(), this, year, month, day)
	}

	fun show(manager: FragmentManager, tag: String? = null, listener: (Date) -> Unit, date: Date? = null) {
		super.show(manager, tag)
		this.listener = listener
		this.date = date
	}

	override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
		calendar.set(year, month, dayOfMonth)
		listener?.invoke(calendar.time)
	}
}
