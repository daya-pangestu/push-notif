package com.daya.taha.presentation.broadcast

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daya.shared.taha.domain.model.Topic
import com.daya.taha.databinding.ItemTopicBinding
import com.daya.taha.utils.diffUtil

class TopicAdapter (
    topicDiffUtil: DiffUtil.ItemCallback<Topic> = diffUtil(
        isItemTheSame = { old, new -> old.topicId == new.topicId },
        isContentTheSame = { old, new -> old == new} ),
    private val onCheckedChangeListener : (Topic, CompoundButton, Boolean) -> Unit,
): ListAdapter<Topic, TopicAdapter.TopicViewHolder>(topicDiffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val binding = ItemTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onCheckedChangeListener)
    }

    inner class TopicViewHolder(private val binding: ItemTopicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Topic, onCheckedChangeListener: (Topic, CompoundButton, Boolean) -> Unit) {
            binding.chipTopic.apply {
                text = item.topicName
                setOnCheckedChangeListener { buttonView, isChecked ->
                    onCheckedChangeListener(item, buttonView, isChecked)
                }
            }
        }
    }
}