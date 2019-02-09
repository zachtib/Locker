package com.zachtib.locker.ui.notes.list

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.zachtib.locker.R
import com.zachtib.locker.framework.ui.FragmentView
import com.zachtib.locker.models.Note
import com.zachtib.locker.ui.screens.NoteAdapter
import kotlinx.android.synthetic.main.fragment_generic_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesListFragment : FragmentView(R.layout.fragment_generic_list) {
    private val viewModel: NotesListViewModel by viewModel()
    private val noteAdapter: NoteAdapter = NoteAdapter()

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
            viewModel.createNote()
        }
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