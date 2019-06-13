package com.oleksandrlysun.traveminder.presentation.screens.cameranotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.domain.models.CameraNote
import com.oleksandrlysun.traveminder.presentation.extensions.ellipse
import com.oleksandrlysun.traveminder.presentation.extensions.toFormat

class CameraNotesAdapter : RecyclerView.Adapter<CameraNotesAdapter.ViewHolder>() {

	var items: List<CameraNote> = ArrayList()
		set(value) {
			field = value
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.item_camera_note, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount() = items.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(items[position])
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		private val title = itemView.findViewById<TextView>(R.id.title)
		private val description = itemView.findViewById<TextView>(R.id.description)
		private val thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)
		private val date = itemView.findViewById<TextView>(R.id.date)

		fun bind(item: CameraNote) {
			title.text = item.title
			description.text = item.description?.ellipse(40)
			date.text = item.date?.toFormat()
			Glide.with(itemView)
					.load(item.picturePath)
					.placeholder(R.drawable.travel)
					.into(thumbnail)
		}
	}
}