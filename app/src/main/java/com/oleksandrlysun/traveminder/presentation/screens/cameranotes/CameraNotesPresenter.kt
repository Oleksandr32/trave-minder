package com.oleksandrlysun.traveminder.presentation.screens.cameranotes

import com.oleksandrlysun.traveminder.domain.interactors.StorageInteractor
import com.oleksandrlysun.traveminder.domain.models.CameraNote
import com.oleksandrlysun.traveminder.presentation.base.Presenter
import com.oleksandrlysun.traveminder.presentation.di.scope.FragmentScope
import com.oleksandrlysun.traveminder.presentation.extensions.contains
import com.oleksandrlysun.traveminder.presentation.extensions.toFormat
import com.oleksandrlysun.traveminder.presentation.navigation.MainNavigation
import javax.inject.Inject

@FragmentScope
class CameraNotesPresenter @Inject constructor(view: CameraNotesView,
                                               private val navigation: MainNavigation,
                                               private val storageInteractor: StorageInteractor)
	: Presenter<CameraNotesView>(view) {

	private var notes = emptyList<CameraNote>()
	private var filteredNotes = emptyList<CameraNote>()

	override fun onViewCreated() {
		super.onViewCreated()
		loadNotes()
	}

	fun search(query: String) {
		if (notes.isEmpty()) return

		filteredNotes = notes.asSequence()
				.filter { filterNotes(it, query) }
				.toList()

		if (filteredNotes.isNotEmpty()) {
			view.setNotes(filteredNotes)
		} else {
			view.setCameraNotesState(CameraNotesState.EMPTY)
		}
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

	private fun filterNotes(note: CameraNote, query: String): Boolean {
		return note.title.contains(query)
				|| note.description.contains(query)
				|| note.date?.toFormat().contains(query)
	}
}