package com.daya.taha.ui.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daya.taha.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemCategoryNotifFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_category_notif, container, false)
    }
}