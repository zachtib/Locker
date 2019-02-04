package com.zachtib.locker.ui.notes.list

import com.zachtib.locker.data.NotesDao
import com.zachtib.locker.framework.ui.CoroutineViewModel

class NotesListViewModel(
    private val notesDao: NotesDao
) : CoroutineViewModel() {

}