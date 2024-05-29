package team.study.composemovieapp.features.feed.presentation.input

interface IFeedViewModelInput {
    fun openDetail(movieName: String)
    fun openInfoDialog()
    fun refreshFeed()
}