package com.study.moviesearch.data.log.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LogEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LogDatabase : RoomDatabase() {
    abstract fun logDao(): LogDao
}
