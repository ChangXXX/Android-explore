package com.study.moviesearch.data.log.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LogDao {
    /**
     * 검색 log 저장
     *
     * @param log 검색 기록 string
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLog(log: LogEntity)

    /**
     * 검색 log 전체 획득
     */
    @Query("SELECT * FROM LogEntity ORDER BY created ASC")
    suspend fun getLogs(): List<LogEntity>

    /**
     * 특정 검색 log 삭제
     */
    @Delete
    suspend fun delete(log: LogEntity)
}
