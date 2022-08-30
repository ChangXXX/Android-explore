package com.study.room.ui.file

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.study.room.databinding.FragmentFileBinding
import com.study.room.util.UiState
import com.study.room.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FileFragment : Fragment() {

    private val vm: FileViewModel by viewModels()
    private lateinit var binding: FragmentFileBinding
    private var folderId = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = vm

        folderId = FileFragmentArgs.fromBundle(requireArguments()).folderId

        setAdapter()
        getFiles(folderId)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.addEventFlow.collect {
                    vm.addFile(binding.tietFile.text.toString(), folderId)
                }
            }
        }
    }

    private fun setAdapter() {
        val adapter = FileAdapter()
        binding.rvFile.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.fileUiState.collect { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            toast("로딩")
                        }
                        is UiState.Success -> {
                            adapter.submitList(uiState.data)
                            binding.tietFile.text?.clear()
                        }
                        is UiState.Error -> {
                            toast("에러")
                        }
                    }
                }
            }
        }
    }

    private fun getFiles(folderId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            vm.getFiles(folderId)
        }
    }
}
