package com.study.room.ui.folder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.study.room.data.folderandfile.db.FolderEntity
import com.study.room.databinding.FragmentFolderBinding
import com.study.room.util.ItemClickListener
import com.study.room.util.UiState
import com.study.room.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FolderFragment :
    Fragment(),
    ItemClickListener<FolderEntity> {
    private val vm: FolderViewModel by viewModels()
    private lateinit var binding: FragmentFolderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFolderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = vm
        setAdapter()
        getFolders()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.eventFlow.collect(::handleEvent)
            }
        }
    }

    private fun getFolders() {
        viewLifecycleOwner.lifecycleScope.launch {
            vm.getFolders()
        }
    }

    private fun setAdapter() {
        val adapter = FolderAdapter(this)
        binding.rvFolder.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.folderUiState.collect { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            toast("로딩")
                        }
                        is UiState.Success -> {
                            adapter.submitList(uiState.data)
                        }
                        is UiState.Error -> {
                            toast("에러")
                        }
                    }
                }
            }
        }
    }

    override fun onItemOpenDetail(folder: FolderEntity) {
        val action = FolderFragmentDirections.actionFolderToFile(folder.id)
        findNavController().navigate(action)
    }

    private fun handleEvent(event: Event) = when (event) {
        is Event.addFolder -> {
            val action = FolderFragmentDirections.actionFolderToAdd()
            findNavController().navigate(action)
        }
    }
}
