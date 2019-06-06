package com.oleksandrlysun.traveminder.data.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtil {

	private const val DATE_FORMAT = "dd MMMM yyyy hh:mm"

	fun formatDate(date: Date): String {
		return SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(date)
	}

	fun parseDate(date: String): Date {
		return SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(date)
	}
}