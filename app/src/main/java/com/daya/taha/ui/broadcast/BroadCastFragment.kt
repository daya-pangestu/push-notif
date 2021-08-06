package com.daya.taha.ui.broadcast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.daya.taha.R
import com.daya.taha.databinding.FragmentBroadcastBinding

class BroadCastFragment : Fragment(R.layout.fragment_broadcast) {

    private val binding by viewBinding<FragmentBroadcastBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}