package com.study.room.ui.file

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.study.room.data.folderandfile.db.FileEntity
import com.study.room.databinding.ItemFileBinding

class FileAdapter : ListAdapter<FileEntity, FileAdapter.FileViewHolder>(FileDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val binding =
            ItemFileBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FileViewHolder(private val binding: ItemFileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(file: FileEntity) {
            binding.file = file
            binding.executePendingBindings()
        }
    }
}

private class FileDiffCallback() : DiffUtil.ItemCallback<FileEntity>() {
    override fun areItemsTheSame(oldItem: FileEntity, newItem: FileEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FileEntity, newItem: FileEntity): Boolean {
        return oldItem == newItem
    }
}
