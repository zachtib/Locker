package com.zachtib.locker.ui.notes.list

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.zachtib.locker.R
import com.zachtib.locker.framework.dataclasses.Resource
import com.zachtib.locker.framework.ui.FragmentView
import com.zachtib.locker.models.Note
import kotlinx.android.synthetic.main.fragment_generic_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class NotesListFragment : FragmentView(R.layout.fragment_generic_list) {
    private val viewModel: NotesListViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.notes.observe {
            when (it) {
                is Resource.Loading -> showLoading()
                is Resource.Content -> showContent(it.value)
                is Resource.Error -> showError(it.throwable)
            }
        }

        addButton.onClick {
            findNavController()
        }
    }

    private fun showLoading() {
        listOf(addButton, recyclerView).forEach { it.isVisible = false }
        progressBar.isVisible = true
    }

    fun showContent(value: List<Note>) {
        listOf(addButton, recyclerView).forEach { it.isVisible = true }
        progressBar.isVisible = false
    }

    private fun showError(t: Throwable) {
        Timber.e(t)
    }
}