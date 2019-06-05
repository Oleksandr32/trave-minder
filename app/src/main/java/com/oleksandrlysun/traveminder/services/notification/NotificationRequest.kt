package com.oleksandrlysun.traveminder.services.notification

import android.os.Parcel
import android.os.Parcelable

data class NotificationRequest(val id: Int,
                               val title: String,
                               val content: String,
                               val triggerAtMillis: Long) : Parcelable {

	constructor(parcel: Parcel) : this(
			parcel.readInt(),
			parcel.readString(),
			parcel.readString(),
			parcel.readLong())

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeInt(id)
		parcel.writeString(title)
		parcel.writeString(content)
		parcel.writeLong(triggerAtMillis)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<NotificationRequest?> {
		override fun createFromParcel(parcel: Parcel): NotificationRequest {
			return NotificationRequest(parcel)
		}

		override fun newArray(size: Int): Array<NotificationRequest?> {
			return arrayOfNulls(size)
		}
	}
}