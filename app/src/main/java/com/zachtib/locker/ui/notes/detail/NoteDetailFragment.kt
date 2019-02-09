package com.zachtib.locker.ui.notes.detail

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.zachtib.locker.R
import com.zachtib.locker.framework.ui.FragmentView
import kotlinx.android.synthetic.main.fragment_note_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteDetailFragment : FragmentView(R.layout.fragment_note_detail, R.menu.note_detail_menu) {
    private val viewModel: NoteDetailViewModel by viewModel()
    private val args: NoteDetailFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.note.observe {
            title.text = it.title
            content.text = it.content
        }
        viewModel.loadNote(args.noteId)
    }
}