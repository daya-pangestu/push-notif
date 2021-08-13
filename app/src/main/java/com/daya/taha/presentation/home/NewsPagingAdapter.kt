package com.daya.taha.presentation.home

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daya.shared.taha.domain.model.News
import com.daya.taha.databinding.ItemNewsBinding
import com.daya.taha.utils.diffUtil
import timber.log.Timber

class NewsPagingAdapter(
    private val onItemClick : (News) -> Unit,
) : PagingDataAdapter<News, NewsPagingAdapter.NewsViewHolder>(
    diffUtil<News>(
        isItemTheSame = { old: News, new: News -> old.senderId == new.senderId},
        isContentTheSame = { old: News, new: News -> old == new }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding, onItemClick)

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        Timber.i("judul ${item?.title}")

        if (item != null) {
            holder.bind(item)
        }
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding, onItemClick: (News) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                getItem(bindingAdapterPosition)?.let {
                    onItemClick(it)
                }
            }
        }

        fun bind(item: News) {
            Glide.with(itemView)
                .load(item.urlImage)
                .placeholder(ColorDrawable(ContextCompat.getColor(itemView.context, android.R.color.darker_gray)))
                .error(ColorDrawable(ContextCompat.getColor(itemView.context, android.R.color.darker_gray)))
                .into(binding.imgInfo)

            binding.tvTitle.text = item.title
            binding.tvDesc.text = item.description

        }
    }
}