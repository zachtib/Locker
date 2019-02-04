package com.zachtib.locker.ui.notes.list

import com.zachtib.locker.R
import com.zachtib.locker.framework.ui.FragmentView
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesListFragment : FragmentView(R.layout.fragment_generic_list) {
    private val viewModel: NotesListViewModel by viewModel()


}