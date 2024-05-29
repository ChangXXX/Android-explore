package team.study.composemovieapp.features.feed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import team.study.composemovieapp.features.common.repository.MovieRepository
import team.study.composemovieapp.features.feed.presentation.input.IFeedViewModelInput
import team.study.composemovieapp.features.feed.presentation.output.FeedState
import team.study.composemovieapp.features.feed.presentation.output.FeedUiEffect
import team.study.composemovieapp.features.feed.presentation.output.IFeedViewModelOutput
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel(), IFeedViewModelOutput, IFeedViewModelInput {

    private val _feedState: MutableStateFlow<FeedState> =
        MutableStateFlow(FeedState.Loading)
    override val feedState: StateFlow<FeedState>
        get() = _feedState

    private val _feedUiEffect: MutableSharedFlow<FeedUiEffect> = MutableSharedFlow<FeedUiEffect>()
    override val feedUiEffect: SharedFlow<FeedUiEffect>
        get() = _feedUiEffect

    fun getMovies() {
        viewModelScope.launch {
            movieRepository.getMovieList()
        }
    }

    override fun openDetail(movieName: String) {
        TODO("Not yet implemented")
    }

    override fun openInfoDialog() {
        TODO("Not yet implemented")
    }

    override fun refreshFeed() {
        TODO("Not yet implemented")
    }
}