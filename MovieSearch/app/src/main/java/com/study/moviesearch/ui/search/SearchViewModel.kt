package com.study.moviesearch.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.moviesearch.data.log.db.LogEntity
import com.study.moviesearch.data.log.repository.LogRepository
import com.study.moviesearch.data.movie.data.Movie
import com.study.moviesearch.data.movie.repository.MovieRepository
import com.study.moviesearch.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val logRepository: LogRepository
) : ViewModel() {

    /**
     * 터치 이벤트 flow
     */
    private val _eventFlow: MutableSharedFlow<Event> =
        MutableSharedFlow()
    val eventFlow = _eventFlow.asSharedFlow()

    /**
     * 영화 state flow
     */
    private val _movieStateFlow: MutableStateFlow<UiState<List<Movie>>> =
        MutableStateFlow(UiState.Empty)
    val movieStateFlow = _movieStateFlow.asStateFlow()

    /**
     * 영화 검색
     *
     * @param title 검색할 영화 이름
     */
    fun getMovies(title: String) {
        viewModelScope.launch {
            _movieStateFlow.emit(UiState.Loading)

            val result = movieRepository.getMovies(title)
            result
                .onSuccess { movies ->
                    val logs = logRepository.getLogs()
                    if (logs.size >= 9) {
                        for (i in 0..logs.size - 10) {
                            logRepository.delete(logs[i])
                        }
                    }

                    val currTime = System.currentTimeMillis()
                    val dateFormat = SimpleDateFormat("yy.mm.dd.hh.mm.ss", Locale.KOREA)
                    logRepository.saveLog(
                        LogEntity(
                            searchName = title,
                            created = dateFormat.format(currTime)
                        )
                    )

                    if (movies.isEmpty()) _movieStateFlow.emit(UiState.Empty)
                    else _movieStateFlow.emit(UiState.Success(movies))
                }.onFailure { exception ->
                    _movieStateFlow.emit(UiState.Error(exception))
                }
        }
    }

    /**
     * 검색 event
     */
    fun searchEvent() {
        event(Event.SearchEvent)
    }

    /**
     * 최근 검색 fragment open event
     */
    fun logEvent() {
        event(Event.LogEvent)
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }
}
