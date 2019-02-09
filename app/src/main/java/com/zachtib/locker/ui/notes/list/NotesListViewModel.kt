package com.zachtib.locker.ui.notes.list

import com.zachtib.locker.data.NotesRepository
import com.zachtib.locker.framework.ui.CoroutineViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class NotesListViewModel(
    private val repository: NotesRepository
) : CoroutineViewModel() {

    val notes = repository.notes

    // TODO
    fun createNote() {
        launch {
            Timber.d("Creating a test note")
            repository.createNote("Test", "Test test test")
        }
    }
}