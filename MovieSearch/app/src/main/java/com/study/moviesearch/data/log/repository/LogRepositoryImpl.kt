package com.study.moviesearch.data.log.repository

import com.study.moviesearch.data.log.db.LogDao
import com.study.moviesearch.data.log.db.LogEntity
import javax.inject.Inject

class LogRepositoryImpl @Inject constructor(
    private val dao: LogDao
) : LogRepository {

    override suspend fun saveLog(log: LogEntity) {
        dao.saveLog(log)
    }

    override suspend fun getLogs(): List<LogEntity> {
        return dao.getLogs()
    }

    override suspend fun delete(log: LogEntity) {
        dao.delete(log)
    }
}
