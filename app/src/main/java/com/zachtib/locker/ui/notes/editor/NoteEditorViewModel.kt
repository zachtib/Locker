package com.zachtib.locker.ui.notes.editor

import com.zachtib.locker.data.NotesRepository
import com.zachtib.locker.framework.ui.CoroutineViewModel

class NoteEditorViewModel(private val repo: NotesRepository) : CoroutineViewModel() {

    suspend fun saveNote(title: CharSequence, content: CharSequence) {
        repo.createNote(title.toString(), content.toString())
    }
}
