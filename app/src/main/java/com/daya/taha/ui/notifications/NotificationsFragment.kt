package com.daya.taha.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.daya.taha.databinding.FragmentNotificationsBinding
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import dagger.hilt.android.AndroidEntryPoint

class NotificationsFragment : Fragment() {

   // private val notificationsViewModel  by viewModels<NotificationsViewModel>()
    private  var _binding : FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentNotificationsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tabLayout.tabMode = MODE_SCROLLABLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}