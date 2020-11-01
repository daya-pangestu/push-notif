package com.daya.taha.ui.dashboard


import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.daya.taha.R
import com.daya.taha.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private val binding : FragmentDashboardBinding by viewBinding()
}