package com.study.moviesearch.ui.log

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.study.moviesearch.data.log.db.LogEntity
import com.study.moviesearch.databinding.ItemLogBinding

class LogAdapter(
    private val onLogClickListener: LogClickListener
) : ListAdapter<LogEntity, LogAdapter.LogViewHolder>(LogDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val binding =
            ItemLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.binding.cvLogMovie.setOnClickListener {
            onLogClickListener.onLogClick(getItem(position))
        }
    }

    inner class LogViewHolder(val binding: ItemLogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(log: LogEntity) {
            binding.log = log
            binding.executePendingBindings()
        }
    }
}

private class LogDiffCallback : DiffUtil.ItemCallback<LogEntity>() {
    override fun areItemsTheSame(oldItem: LogEntity, newItem: LogEntity): Boolean {
        return oldItem.searchName == newItem.searchName
    }

    override fun areContentsTheSame(oldItem: LogEntity, newItem: LogEntity): Boolean {
        return oldItem == newItem
    }
}
