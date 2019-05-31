package com.oleksandrlysun.traveminder.presentation.views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.*
import androidx.core.view.children
import androidx.core.widget.ImageViewCompat
import com.google.android.flexbox.FlexboxLayout
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.extensions.hideSoftInput
import com.oleksandrlysun.traveminder.presentation.extensions.showSoftInput
import com.oleksandrlysun.traveminder.presentation.extensions.visibleOrGone

class TagsView @JvmOverloads constructor(context: Context,
                                         attrs: AttributeSet? = null,
                                         defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

	private val tagsContainer by lazy { findViewById<FlexboxLayout>(R.id.container_tags) }
	private val manageContainer by lazy { findViewById<LinearLayout>(R.id.container_manage) }
	private val manageButton by lazy { findViewById<RelativeLayout>(R.id.btn_manage_tags) }
	private val manageImageView by lazy { findViewById<ImageView>(R.id.manage_icon) }
	private val manageLabel by lazy { findViewById<TextView>(R.id.manage_label) }
	private val doneButton by lazy { findViewById<ImageView>(R.id.btn_done) }
	private val cancelButton by lazy { findViewById<ImageView>(R.id.btn_cancel) }
	private val tagEditText by lazy { findViewById<AutoCompleteTextView>(R.id.et_tag) }

	var controlColor: Int = 0

	init {
		LayoutInflater.from(context).inflate(R.layout.view_tags, this, true)
		attrs?.let {
			val typedArray = context.obtainStyledAttributes(it, R.styleable.TagsView, 0, 0)
			controlColor = typedArray.getColor(R.styleable.TagsView_controlColor, 0)
			typedArray.recycle()
		}
		setupViews()
	}

	fun addTagViewByName(name: String) {
		if (!isTagExist(name)) {
			val tagView = TagView(context).apply {
				this.name = name
				setOnClickListener { onTagClick(it as TagView) }
			}
			tagsContainer.addView(tagView, 0)
		}
	}

	private fun setupViews() {
		ImageViewCompat.setImageTintList(manageImageView, ColorStateList.valueOf(controlColor))
		ImageViewCompat.setImageTintList(doneButton, ColorStateList.valueOf(controlColor))
		ImageViewCompat.setImageTintList(cancelButton, ColorStateList.valueOf(controlColor))

		manageLabel.setTextColor(controlColor)

		manageButton.setOnClickListener { manageTags() }
		doneButton.setOnClickListener { doneManage() }
		cancelButton.setOnClickListener { finishManage() }

		tagEditText.setAdapter(ArrayAdapter<String>(context,
				R.layout.item_suggestion,
				resources.getStringArray(R.array.note_tags)))
	}

	private fun manageTags() {
		toggleTagsDeletable()
		setManageContainerVisibility(true)
		tagEditText.showSoftInput()
	}

	private fun doneManage() {
		val name = tagEditText.text.toString()
		if (name.isNotBlank()) {
			addTagViewByName(name)
		}
		finishManage()
	}

	private fun finishManage() {
		toggleTagsDeletable()
		tagEditText.hideSoftInput()
		setManageContainerVisibility(false)
		tagEditText.text.clear()
	}

	private fun setManageContainerVisibility(visible: Boolean) {
		manageButton.visibleOrGone = !visible
		manageContainer.visibleOrGone = visible
	}

	private fun onTagClick(tag: TagView) {
		if (tag.isDeletable) {
			tagsContainer.removeView(tag)
		}
	}

	private fun toggleTagsDeletable() {
		tagsContainer.children
				.filterIsInstance<TagView>()
				.forEach { it.toggleDeletable() }
	}

	private fun isTagExist(name: String): Boolean {
		return tagsContainer.children
				.filterIsInstance<TagView>()
				.any { it.name == name }
	}
}