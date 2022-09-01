package com.study.moviesearch.ui.log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.study.moviesearch.databinding.FragmentLogBinding
import dagger.hilt.android.AndroidEntryPoint

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
    }

    override fun onLogClick(log: String) {
        TODO("Not yet implemented")
    }
}
