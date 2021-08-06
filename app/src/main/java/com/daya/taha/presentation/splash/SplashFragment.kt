package com.daya.taha.presentation.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.daya.shared.taha.data.Resource
import com.daya.taha.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            Timber.i("starting to  delay")
            delay(1000)
            Timber.i("delayed loading for 300 ms")
            observeLoginStatus()
        }
    }

    private fun observeLoginStatus() {
        viewModel.isUserLoggedIn.observe(viewLifecycleOwner){
            when (it) {
                is Resource.Loading ->  Timber.wtf("impossible loading state : ")
                is Resource.Success -> {
                    if (it.data) {
                        findNavController().navigate(R.id.action_splashFragment_to_home)
                    } else {
                        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                    }
                }
                is Resource.Error -> Timber.wtf("impossible error state : ${it.exceptionMessage} ")
            }
        }
    }
}