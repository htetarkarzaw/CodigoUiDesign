package com.htetarkarzaw.codigouidesign.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.htetarkarzaw.codigouidesign.databinding.ViewHolderCardBinding

class CardAdapter(
    private val onClickDetail: (CardVO) -> Unit
) :
    ListAdapter<CardVO, RecyclerView.ViewHolder>(cardDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ViewHolderCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CardViewHolder).bind(getItem(position))
    }

    companion object {
        val cardDiffUtil = object : DiffUtil.ItemCallback<CardVO>() {
            override fun areItemsTheSame(oldItem: CardVO, newItem: CardVO): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CardVO, newItem: CardVO): Boolean {
                return oldItem.title == newItem.title
            }

        }
    }

    inner class CardViewHolder(private val binding: ViewHolderCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CardVO) {
            binding.tvTitle.text = item.title
            binding.tvContent.text = item.content
            binding.tvAction.text = item.action
            binding.ivIcon.setImageResource(item.icon)
            binding.root.setOnClickListener {
                onClickDetail(item)
            }
        }
    }
}