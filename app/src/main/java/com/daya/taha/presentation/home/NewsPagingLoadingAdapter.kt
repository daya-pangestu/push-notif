package com.daya.taha.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daya.taha.R
import com.daya.taha.databinding.LayoutLoadingBinding

class NewsPagingLoadingAdapter(
    private val retry : () -> Unit
) : LoadStateAdapter<NewsPagingLoadingAdapter.LoadStateItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) : LoadStateItemViewHolder {
        val binding = LayoutLoadingBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_loading, parent, false))
        return LoadStateItemViewHolder(binding,retry)
    }


    override fun onBindViewHolder(holder: LoadStateItemViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    class LoadStateItemViewHolder(
        private val binding: LayoutLoadingBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retryCallback() }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState is LoadState.Error
                errorMsg.isVisible =
                    !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                errorMsg.text = (loadState as? LoadState.Error)?.error?.message
            }
        }
    }
}