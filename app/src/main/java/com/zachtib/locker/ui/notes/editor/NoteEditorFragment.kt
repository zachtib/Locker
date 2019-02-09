package com.zachtib.locker.ui.notes.editor

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.zachtib.locker.R
import com.zachtib.locker.framework.ui.FragmentView
import kotlinx.android.synthetic.main.fragment_note_editor.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteEditorFragment : FragmentView(R.layout.fragment_note_editor) {
    private val viewModel: NoteEditorViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        floatingActionButton.onClick {
            viewModel.saveNote(titleEditText.text, contentEditText.text)
            findNavController().navigateUp()
        }
    }
}