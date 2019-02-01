package com.zachtib.locker

import com.zachtib.locker.data.LockerDatabase
import org.koin.dsl.module.module

val appModule = module {
    single { LockerDatabase.build(get())}
}