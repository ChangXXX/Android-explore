package com.study.moviesearch.ui.log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.study.moviesearch.data.log.db.LogEntity
import com.study.moviesearch.databinding.FragmentLogBinding
import com.study.moviesearch.utils.UiState
import com.study.moviesearch.utils.extensions.hide
import com.study.moviesearch.utils.extensions.repeatOnStarted
import com.study.moviesearch.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogFragment : Fragment(), LogClickListener {
    private val vm: LogViewModel by viewModels()
    private lateinit var binding: FragmentLogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = vm
        initAdapter()
        getLogs()
    }

    private fun initAdapter() {
        val adapter = LogAdapter(this)
        binding.rvLog.adapter = adapter

        repeatOnStarted {
            launch {
                vm.logStateFlow.collect { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            binding.progressBarLog.show()
                        }
                        is UiState.Success -> {
                            adapter.submitList(uiState.data)
                            binding.progressBarLog.hide(true)
                        }
                        is UiState.Error -> {
                            Toast.makeText(context, "${uiState.error} 오류 발생", Toast.LENGTH_SHORT)
                                .show()
                            binding.progressBarLog.hide(true)
                        }
                        is UiState.Empty -> {
                            Toast.makeText(context, "검색 기록이 없습니다.", Toast.LENGTH_SHORT)
                                .show()
                            binding.progressBarLog.hide(true)
                        }
                    }
                }
            }
        }
    }

    private fun getLogs() {
        repeatOnStarted {
            vm.getLogs()
        }
    }

    override fun onLogClick(log: LogEntity) {
        val direction = LogFragmentDirections
        findNavController().currentDestination?.getAction(direction.actionLogToSearch(log.searchName).actionId)
            .run {
                findNavController().navigate(direction.actionLogToSearch(log.searchName))
            }
    }
}
