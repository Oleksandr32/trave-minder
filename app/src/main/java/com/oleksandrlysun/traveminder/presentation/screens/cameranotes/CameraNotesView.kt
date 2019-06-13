package com.oleksandrlysun.traveminder.presentation.screens.cameranotes

import com.oleksandrlysun.traveminder.domain.models.CameraNote
import com.oleksandrlysun.traveminder.presentation.base.BaseView

interface CameraNotesView : BaseView {

	fun setCameraNotesState(state: CameraNotesState)

	fun setNotes(notes: List<CameraNote>)
}