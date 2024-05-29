package team.study.composemovieapp.features.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import team.study.composemovieapp.features.feed.presentation.viewmodel.FeedViewModel
import team.study.composemovieapp.ui.theme.ComposeMovieAppTheme

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private val viewModel by viewModels<FeedViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposeMovieAppTheme {
                    Text(text = "FeedFragment")
                }
            }
        }
    }
}