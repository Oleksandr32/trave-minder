package com.oleksandrlysun.traveminder.presentation.screens.cameranotes

import com.oleksandrlysun.traveminder.domain.interactors.StorageInteractor
import com.oleksandrlysun.traveminder.domain.models.CameraNote
import com.oleksandrlysun.traveminder.presentation.base.Presenter
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import javax.inject.Inject

class CameraNotesPresenter @Inject constructor(view: CameraNotesView,
                                               private val navigation: MainNavigation,
                                               private val storageInteractor: StorageInteractor)
	: Presenter<CameraNotesView>(view) {

	private var notes = emptyList<CameraNote>()

	override fun onViewCreated() {
		super.onViewCreated()
		loadNotes()
	}

	fun addNote() {
		navigation.tabsToCamera()
	}

	private fun loadNotes() {
		launchWithHandler {
			try {
				view.setCameraNotesState(CameraNotesState.LOADING)

				notes = storageInteractor.getAll()
				if (notes.isNotEmpty()) {
					view.setNotes(notes)
					view.setCameraNotesState(CameraNotesState.LOADED)
				} else {
					view.setCameraNotesState(CameraNotesState.EMPTY)
				}
			} catch (e: Throwable) {
				view.setCameraNotesState(CameraNotesState.LOADING)
				e.printStackTrace()
			}
		}
	}
}