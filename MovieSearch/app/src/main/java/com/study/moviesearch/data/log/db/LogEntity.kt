package com.study.moviesearch.data.log.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class LogEntity(
    @PrimaryKey
    val searchName: String,
    val created: String
)
