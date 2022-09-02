package com.study.moviesearch.data.log.repository

import com.study.moviesearch.data.log.db.LogEntity

interface LogRepository {

    /**
     * 검색 log 저장
     *
     * @param log 검색 기록 string
     */
    suspend fun saveLog(log: LogEntity)

    /**
     * 검색 log 전체 획득
     */
    suspend fun getLogs(): List<LogEntity>

    /**
     * 특정 검색 log 삭제
     */
    suspend fun delete(log: LogEntity)
}
