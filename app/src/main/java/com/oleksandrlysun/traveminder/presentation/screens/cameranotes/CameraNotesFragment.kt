package com.oleksandrlysun.traveminder.presentation.screens.cameranotes

import android.view.Menu
import android.view.MenuInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.oleksandrlysun.traveminder.R
import com.oleksandrlysun.traveminder.domain.models.CameraNote
import com.oleksandrlysun.traveminder.presentation.base.PresenterFragment
import com.oleksandrlysun.traveminder.presentation.extensions.hideSoftInput
import com.oleksandrlysun.traveminder.presentation.extensions.visibleOrGone
import com.oleksandrlysun.traveminder.presentation.screens.cameranotes.CameraNotesState.*
import com.oleksandrlysun.traveminder.utils.DividerItemDecoration
import com.oleksandrlysun.traveminder.utils.lazy.ResettableLazyManager
import com.oleksandrlysun.traveminder.utils.resettableLazy
import javax.inject.Inject

class CameraNotesFragment : PresenterFragment(), CameraNotesView {

	@Inject
	lateinit var presenter: CameraNotesPresenter

	private val resettableLazyManager = ResettableLazyManager()
	private val notesAdapter = CameraNotesAdapter()

	private val notesRecyclerView by resettableLazy(resettableLazyManager) { view!!.findViewById<RecyclerView>(R.id.rv_camera_notes) }
	private val emptyContainer by resettableLazy(resettableLazyManager) { view!!.findViewById<ImageView>(R.id.container_empty) }
	private val loading by resettableLazy(resettableLazyManager) { view!!.findViewById<LinearLayout>(R.id.loading) }
	private val addNoteButton by resettableLazy(resettableLazyManager) { view!!.findViewById<FloatingActionButton>(R.id.fab_add_camera_note) }

	override val layoutResId = R.layout.fragment_camera_notes

	override fun getPresenters() = listOf(presenter)

	override fun onDestroyView() {
		resettableLazyManager.reset()
		super.onDestroyView()
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		super.onCreateOptionsMenu(menu, inflater)
		inflater.inflate(R.menu.searchable_menu, menu)

		val searchView = menu.findItem(R.id.action_search)?.actionView as? SearchView
		searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String): Boolean {
				return false
			}

			override fun onQueryTextChange(newText: String): Boolean {
				presenter.search(newText)
				return false
			}
		})
	}

	override fun setupUI() {
		super.setupUI()
		addNoteButton.setOnClickListener { onAddNoteButtonClick() }
		notesRecyclerView.layoutManager = LinearLayoutManager(context)
		notesRecyclerView.adapter = notesAdapter
		notesRecyclerView.addItemDecoration(DividerItemDecoration(requireContext()))
	}

	override fun setupToolbar() {
		super.setupToolbar()
		setHasOptionsMenu(true)
	}

	override fun setCameraNotesState(state: CameraNotesState) {
		when (state) {
			LOADING -> {
				loading.isVisible = true
				emptyContainer.isVisible = false
				notesRecyclerView.isVisible = false
			}
			LOADED -> {
				loading.isVisible = false
				emptyContainer.isVisible = false
				notesRecyclerView.isVisible = true

			}
			EMPTY -> {
				loading.isVisible = false
				notesRecyclerView.isVisible = false
				emptyContainer.isVisible = true
			}
		}
	}

	override fun setNotes(notes: List<CameraNote>) {
		notesAdapter.items = notes
	}

	private fun onAddNoteButtonClick() {
		view?.hideSoftInput()
		presenter.addNote()
	}
}