package com.htetarkarzaw.codigouidesign.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.htetarkarzaw.codigouidesign.databinding.ViewHolderShowBinding

class ShowAdapter(
    private val onClickDetail: (ShowVO) -> Unit
) :
    ListAdapter<ShowVO, RecyclerView.ViewHolder>(showDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ViewHolderShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ShowViewHolder).bind(getItem(position))
    }

    companion object {
        val showDiffUtil = object : DiffUtil.ItemCallback<ShowVO>() {
            override fun areItemsTheSame(oldItem: ShowVO, newItem: ShowVO): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ShowVO, newItem: ShowVO): Boolean {
                return oldItem.title == newItem.title
            }

        }
    }

    inner class ShowViewHolder(private val binding: ViewHolderShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShowVO) {
            binding.tvTitle.text = item.title
            binding.tvDate.text = item.time
            binding.ivImage.setImageResource(item.image)
            binding.root.setOnClickListener {
                onClickDetail(item)
            }
        }
    }
}