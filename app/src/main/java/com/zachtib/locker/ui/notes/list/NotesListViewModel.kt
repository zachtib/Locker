package com.zachtib.locker.ui.notes.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zachtib.locker.data.NotesDao
import com.zachtib.locker.framework.dataclasses.Resource
import com.zachtib.locker.framework.ui.CoroutineViewModel
import com.zachtib.locker.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NotesListViewModel(
    private val notesDao: NotesDao
) : CoroutineViewModel() {

    private val _notes = MutableLiveData<Resource<List<Note>>>()

    val notes = _notes as LiveData<Resource<List<Note>>>

    init {
        _notes.value = Resource.Loading
        launch {
            val result = async(Dispatchers.IO) { notesDao.getAll() }
            _notes.postValue(Resource.Content(result.await()))
        }
    }
}