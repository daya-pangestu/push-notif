package com.daya.taha.presentation.detail

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.daya.taha.R
import com.daya.taha.databinding.FragmentDetailBinding
import com.daya.taha.presentation.broadcast.TopicAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding  by viewBinding<FragmentDetailBinding>()
    private val args : DetailFragmentArgs by navArgs()

    private val placeholderImage by lazy {
        ColorDrawable(ContextCompat.getColor(requireContext(), android.R.color.darker_gray))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val news = args.newsArg

        val topicAdapter = TopicAdapter{_,_,_ ->

        }.apply {
            submitList(news?.topics)
        }

        with(binding) {
            Glide.with(this@DetailFragment)
                .load(news?.urlImage)
                .placeholder(placeholderImage)
                .into(imgBanner)

            tvUrlAccess.apply {
                text = news?.urlAccess
                movementMethod = ScrollingMovementMethod()
                setHorizontallyScrolling(true)
            }
            tvTitle.text = news?.title
            tvDesc.text = news?.description


            btnShareLink.setOnClickListener {
                val shareLink = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT,news?.urlAccess)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(shareLink, "share link")
                startActivity(shareIntent)
            }

            rvTopic.apply {
                layoutManager = FlexboxLayoutManager(requireContext()).apply {
                    flexDirection = FlexDirection.ROW
                    justifyContent = JustifyContent.FLEX_START
                }
                adapter = topicAdapter
            }

        }
    }
    
    companion object{
        const val DETAIL_NEWS_KEY = "detail_news_key"
    }

}