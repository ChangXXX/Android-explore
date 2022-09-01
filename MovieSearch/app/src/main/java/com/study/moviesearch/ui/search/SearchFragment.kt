package com.study.moviesearch.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.study.moviesearch.databinding.FragmentSearchBinding
import com.study.moviesearch.utils.UiState
import com.study.moviesearch.utils.extensions.hide
import com.study.moviesearch.utils.extensions.repeatOnStarted
import com.study.moviesearch.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(), MovieClickListener {
    private val vm: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = vm

        initAdapter()

        repeatOnStarted {
            launch {
                vm.eventFlow.collect { event ->
                    handleEvent(event)
                    hideKeyboard()
                }
            }
        }
    }

    private fun initAdapter() {
        val adapter = MovieAdapter(this)
        binding.rvMovie.adapter = adapter

        repeatOnStarted {
            launch {
                vm.movieStateFlow.collect { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            binding.progressBar.show()
                        }
                        is UiState.Success -> {
                            adapter.submitList(uiState.data)
                            binding.progressBar.hide(true)
                        }
                        is UiState.Error -> {
                            Toast.makeText(context, "${uiState.error} 오류 발생", Toast.LENGTH_SHORT)
                                .show()
                            binding.progressBar.hide(true)
                        }
                        is UiState.Empty -> {
                            Toast.makeText(context, "영화를 검색해주세요", Toast.LENGTH_SHORT)
                                .show()
                            binding.progressBar.hide(true)
                        }
                    }
                }
            }
        }
    }

    private fun handleEvent(event: Event) = when (event) {
        is Event.SearchEvent -> {
            if (binding.edtSearch.text.toString().isNotEmpty()) {
                vm.getMovies(binding.edtSearch.text.toString())
            } else Toast.makeText(context, "검색할 내용이 없습니다.", Toast.LENGTH_SHORT).show()
        }
        is Event.LogEvent -> {
        }
    }

    private fun hideKeyboard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.edtSearch.windowToken, 0)
    }

    override fun onMovieOpenEvent(link: String) {
        val action = SearchFragmentDirections.actionSearchToMovie(link)
        findNavController().navigate(action)
    }
}
