package com.zachtib.locker.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zachtib.locker.models.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM Note")
    suspend fun getAll(): List<Note>

    @Query("SELECT * FROM Note")
    fun liveGetAll(): LiveData<List<Note>>

    @Insert
    fun insert(note: Note)
}