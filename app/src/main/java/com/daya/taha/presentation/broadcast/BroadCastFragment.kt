package com.daya.taha.presentation.broadcast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.model.News
import com.daya.shared.taha.domain.model.Topic
import com.daya.taha.R
import com.daya.taha.databinding.FragmentBroadcastBinding
import com.daya.taha.utils.toast
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.lang.Exception

@AndroidEntryPoint
class BroadCastFragment : Fragment(R.layout.fragment_broadcast) {

    private val binding by viewBinding<FragmentBroadcastBinding>()
    private val viewModel by viewModels<BroadCastViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isImgChosen(false)
        binding.btnBrowseImg.setOnClickListener {
            pickImage.launch("image/*")
        }

        val topicAdapter = TopicAdapter{ topic: Topic, _, isChecked: Boolean ->
            if (isChecked) {
                viewModel.pickTopic(topic)
            } else {
                viewModel.removeTopic(topic)
            }
        }

        binding.rvBroadcastTopic.apply {
            layoutManager = FlexboxLayoutManager(context).also {
                it.flexDirection = FlexDirection.ROW
                it.justifyContent = JustifyContent.FLEX_START
            }
            adapter = topicAdapter
        }

        viewModel.getTopic().observe(viewLifecycleOwner){
            when (it) {
                is Resource.Loading ->{
                    setBtnBroadCastEnabled(false)
                }
                is Resource.Success -> {
                    val topicCheckAble = it.data.map { topic ->
                        topic.isUnsubscribeAble = false
                        topic
                    }
                    topicAdapter.submitList(topicCheckAble)
                    setBtnBroadCastEnabled(true)
                }
                is Resource.Error -> {
                    context?.toast("error for getting topic to subscribe")
                    setBtnBroadCastEnabled(false)
                }
            }
        }

        binding.btnBroadcast.setOnClickListener {
            val titleText = binding.edTitle.text.toString()
            val descText = binding.edDesc.text.toString()
            val urlAccess = binding.edUrlAccess.text.toString()
            val uriLocalImage = viewModel.getUriImage().value.toString()

            val chosenTopics = viewModel.getChosenTopic()

            if (chosenTopics.isEmpty()) {
                Toast.makeText(it.context, "atleast 1 chip must be checked", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (titleText.isEmpty()) {
                Toast.makeText(it.context, "title mustn't empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (descText.isEmpty()) {
                Toast.makeText(it.context, "desc mustn't empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (urlAccess.isEmpty()) {
                Toast.makeText(it.context, "url access mustn't empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.broadcastNews(
                News(
                    title = titleText,
                    description = descText,
                    urlAccess = urlAccess,
                    topics = chosenTopics.toList(),
                    urlImage = uriLocalImage
                )
            )
        }

        viewModel.broadcastingLiveData.observe(viewLifecycleOwner) {

        }

        viewModel.getUriImage().observe(viewLifecycleOwner){
            Glide.with(this@BroadCastFragment)
                .load(it)
                .into(binding.imgChosenPic)
            isImgChosen(it != null)
        }

        binding.btnDelImg.setOnClickListener {
            viewModel.deleteUriImage()
        }
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()){
        try {
            val uri = it ?: throw IllegalStateException("uri is null")
            val uriPath = uri.path ?: throw IllegalStateException("uri path is null")
            viewModel.setUriImage(uri)

            val fileName = File(uriPath).name
            binding.txtImgName.text = fileName

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setBtnBroadCastEnabled(isEnabled: Boolean) {
        binding.btnBroadcast.isEnabled = isEnabled
    }

    private fun isImgChosen(showImage: Boolean) {
        with(binding) {
            txtImgRatio.isVisible = !showImage
            btnBrowseImg.isVisible = !showImage

            imgChosenPic.isVisible = showImage
            btnDelImg.isVisible = showImage
        }
    }
}