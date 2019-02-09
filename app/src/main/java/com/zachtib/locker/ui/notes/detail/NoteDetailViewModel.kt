package com.zachtib.locker.ui.notes.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zachtib.locker.data.NotesRepository
import com.zachtib.locker.framework.ui.CoroutineViewModel
import com.zachtib.locker.models.Note
import kotlinx.coroutines.launch

class NoteDetailViewModel(private val repo: NotesRepository) : CoroutineViewModel() {
    private val _note = MutableLiveData<Note>()

    val note: LiveData<Note> = _note

    fun loadNote(id: Int) {
        launch {
            val loadedNote = repo.getNote(id)
            _note.postValue(loadedNote)
        }
    }
}