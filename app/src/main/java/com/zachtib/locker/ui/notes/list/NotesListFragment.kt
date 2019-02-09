package com.zachtib.locker.ui.notes.list

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zachtib.locker.R
import com.zachtib.locker.framework.ui.FragmentView
import com.zachtib.locker.models.Note
import com.zachtib.locker.ui.notes.NoteAdapter
import kotlinx.android.synthetic.main.fragment_generic_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesListFragment : FragmentView(R.layout.fragment_generic_list) {
    private val viewModel: NotesListViewModel by viewModel()
    private val noteAdapter = NoteAdapter(this::onNoteClick)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }

        showLoading()
        viewModel.notes.observe {
            showContent(it)
        }

        addButton.onClick {
            findNavController().navigate(R.id.action_notesListFragment_to_noteEditorFragment)
        }
    }

    private fun onNoteClick(note: Note) {
        findNavController()
            .navigate(
                R.id.action_notesListFragment_to_noteDetailFragment,
                bundleOf("noteId" to note.id)
            )
    }

    private fun showLoading() {
        listOf(addButton, recyclerView).forEach { it.isVisible = false }
        progressBar.isVisible = true
    }

    private fun showContent(notes: List<Note>) {
        listOf(addButton, recyclerView).forEach { it.isVisible = true }
        progressBar.isVisible = false
        noteAdapter.submitList(notes)
    }
}