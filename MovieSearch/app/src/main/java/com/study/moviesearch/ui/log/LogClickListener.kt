package com.study.moviesearch.ui.log

import com.study.moviesearch.data.log.db.LogEntity

interface LogClickListener {
    fun onLogClick(log: LogEntity)
}
