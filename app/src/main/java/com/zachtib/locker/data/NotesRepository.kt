package com.zachtib.locker.data

import androidx.lifecycle.LiveData
import com.zachtib.locker.models.Note

class NotesRepository(private val dao: NotesDao) {
    val notes: LiveData<List<Note>> by lazy {
        dao.liveGetAll()
    }

    suspend fun getNote(id: Int) = dao.getNote(id)

    suspend fun createNote(title: String, content: String): Note {
        val newNote = Note(title = title, content = content)
        val id = dao.insert(newNote)
        return newNote.copy(id = id.toInt())
    }
}