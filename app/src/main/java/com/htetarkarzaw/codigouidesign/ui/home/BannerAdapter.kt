package com.htetarkarzaw.codigouidesign.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.htetarkarzaw.codigouidesign.databinding.ViewHolderBannerBinding

class BannerAdapter(
    private val onClickDetail: (Int) -> Unit
) :
    ListAdapter<Int, RecyclerView.ViewHolder>(newDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ViewHolderBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FeedViewHolder).bind(getItem(position))
    }

    companion object {
        val newDiffUtil = object : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class FeedViewHolder(private val binding: ViewHolderBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(id: Int) {
            binding.ivImage.setImageResource(id)
            binding.root.setOnClickListener {
                onClickDetail(id)
            }
        }
    }
}