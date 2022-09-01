package com.study.moviesearch.ui.search

sealed class Event {
    object SearchEvent : Event()
    object LogEvent : Event()
}

interface MovieClickListener {
    fun onMovieOpenEvent(link: String)
}
