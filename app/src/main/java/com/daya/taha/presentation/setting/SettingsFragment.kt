package com.daya.taha.presentation.setting

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.daya.shared.taha.data.Resource
import com.daya.taha.R
import com.daya.taha.databinding.SettingsFragmentBinding
import com.daya.taha.presentation.broadcast.TopicAdapter
import com.daya.taha.utils.toast
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.settings_fragment) {
    private val binding by viewBinding<SettingsFragmentBinding>()
    private val viewModel by viewModels<SettingsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topicAdapter = TopicAdapter{ topic,compoundView, isChecked ->
            if (!isChecked) {
                viewModel.unsubscribeTopic(topic)
                context?.toast("subscribing to ${topic.topicName}")
            } else {
                viewModel.subscribeTopic(topic)
                context?.toast("unSubscribing from ${topic.topicName}")
            }
        }
        binding.rvTopic.apply {
            layoutManager = FlexboxLayoutManager(context).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }
            adapter = topicAdapter
        }

        viewModel.getTopicWithSubscribedStatusLiveData.observe(viewLifecycleOwner){
            when (it) {
                is Resource.Loading -> {
                    setProgress(true)
                }
                is Resource.Success -> {
                    setProgress(false)
                    val topicList = it.data
                    topicAdapter.submitList(topicList)
                }
                is Resource.Error -> {
                    setProgress(false)
                    context?.toast("error ${it.exceptionMessage}", Toast.LENGTH_LONG)
                }
            }
        }
    }

    private fun setProgress(isVisible : Boolean) {
        binding.progressBar.isVisible = isVisible
        binding.tvTopic.isVisible = !isVisible
        binding.rvTopic.isVisible = !isVisible
    }
}