package com.zachtib.locker

import com.zachtib.locker.data.LockerDatabase
import com.zachtib.locker.ui.notes.list.NotesListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { LockerDatabase.build(get())}

    viewModel { NotesListViewModel(get()) }
}