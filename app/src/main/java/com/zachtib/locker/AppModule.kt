package com.zachtib.locker

import com.zachtib.locker.data.LockerDatabase
import com.zachtib.locker.data.NotesRepository
import com.zachtib.locker.ui.notes.detail.NoteDetailViewModel
import com.zachtib.locker.ui.notes.editor.NoteEditorViewModel
import com.zachtib.locker.ui.notes.list.NotesListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { LockerDatabase.build(get()) }

    single { get<LockerDatabase>().notesDao() }

    single { NotesRepository(get()) }

    viewModel { NotesListViewModel(get()) }
    viewModel { NoteDetailViewModel(get()) }
    viewModel { NoteEditorViewModel(get()) }
}