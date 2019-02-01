package com.zachtib.locker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [], version = 1)
abstract class LockerDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "locker-db"

        fun build(context: Context): LockerDatabase {
            return Room.databaseBuilder(context, LockerDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}