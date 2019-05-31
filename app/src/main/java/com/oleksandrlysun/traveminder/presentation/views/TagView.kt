package com.oleksandrlysun.traveminder.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.presentation.extensions.visibleOrGone

class TagView @JvmOverloads constructor(context: Context,
                                        attrs: AttributeSet? = null,
                                        defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

	var name: String? = null
		set(value) {
			field = value
			nameTextView.text = value
		}

	var isDeletable = true
		set(value) {
			field = value
			deleteContainer.visibleOrGone = value
		}

	private val nameTextView by lazy { findViewById<TextView>(R.id.tv_name) }
	private val deleteContainer by lazy { findViewById<LinearLayout>(R.id.container_delete) }

	init {
		LayoutInflater.from(context).inflate(R.layout.view_tag, this, true)
		isClickable = true
		isFocusable = true
		layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
			marginEnd = this@TagView.resources.getDimensionPixelSize(R.dimen.tag_view_margin)
		}
		attrs?.let {
			val typedArray = context.obtainStyledAttributes(it, R.styleable.TagView, 0, 0)
			name = typedArray.getString(R.styleable.TagView_name)
			typedArray.recycle()
		}
	}

	fun toggleDeletable() {
		isDeletable = !isDeletable
	}
}