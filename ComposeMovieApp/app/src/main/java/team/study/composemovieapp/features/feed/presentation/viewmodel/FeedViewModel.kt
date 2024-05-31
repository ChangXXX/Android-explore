package team.study.composemovieapp.features.feed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import team.study.composemovieapp.features.common.entity.EntityWrapper
import team.study.composemovieapp.features.feed.domain.usecase.GetFeedCategoryUseCase
import team.study.composemovieapp.features.feed.presentation.input.IFeedViewModelInput
import team.study.composemovieapp.features.feed.presentation.output.FeedState
import team.study.composemovieapp.features.feed.presentation.output.FeedUiEffect
import team.study.composemovieapp.features.feed.presentation.output.IFeedViewModelOutput
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getFeedCategoryUseCase: GetFeedCategoryUseCase
) : ViewModel(), IFeedViewModelOutput, IFeedViewModelInput {

    val output: IFeedViewModelOutput = this
    val input: IFeedViewModelInput = this

    private val _feedState: MutableStateFlow<FeedState> =
        MutableStateFlow(FeedState.Loading)
    override val feedState: StateFlow<FeedState>
        get() = _feedState

    private val _feedUiEffect: MutableSharedFlow<FeedUiEffect> = MutableSharedFlow<FeedUiEffect>()
    override val feedUiEffect: SharedFlow<FeedUiEffect>
        get() = _feedUiEffect

    init {

        fetchFeed()
    }

    private fun fetchFeed() {
        viewModelScope.launch {
            _feedState.value = FeedState.Loading

            val categories = getFeedCategoryUseCase()
            _feedState.value = when (categories) {
                is EntityWrapper.Success -> {
                    FeedState.Main(
                        categories = categories.entity
                    )
                }
                is EntityWrapper.Fail -> {
                    FeedState.Failed(categories.error.message ?: "Unknown Error")
                }
            }
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