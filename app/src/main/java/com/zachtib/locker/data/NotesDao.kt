package com.zachtib.locker.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
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
    suspend fun insert(note: Note): Long

    @Query("SELECT * FROM Note WHERE id=:id")
    suspend fun getNote(id: Int): Note

    @Delete
    suspend fun delete(note: Note)
}