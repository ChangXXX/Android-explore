package com.study.room.ui.folder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.study.room.data.folderandfile.db.FolderEntity
import com.study.room.databinding.ItemFolderBinding
import com.study.room.util.ItemClickListener

class FolderAdapter(
    private val itemOpenDetail: ItemClickListener<FolderEntity>
) :
    ListAdapter<FolderEntity, FolderAdapter.FolderViewHolder>(FolderDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val binding =
            ItemFolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FolderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.cardFolder.setOnClickListener {
            itemOpenDetail.onItemOpenDetail(item)
        }
    }

    class FolderViewHolder(val binding: ItemFolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(folder: FolderEntity) {
            binding.folder = folder
            binding.executePendingBindings()
        }
    }
}

private class FolderDiffCallback : DiffUtil.ItemCallback<FolderEntity>() {
    override fun areItemsTheSame(oldItem: FolderEntity, newItem: FolderEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FolderEntity, newItem: FolderEntity): Boolean {
        return oldItem == newItem
    }
}
