package com.daya.taha.presentation.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import by.kirich1409.viewbindingdelegate.viewBinding
import com.daya.shared.taha.data.Resource
import com.daya.taha.R
import com.daya.taha.databinding.FragmentHomeBinding
import com.daya.taha.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()

        when (item.itemId) {
            R.id.action_logout -> {
                lifecycleScope.launch {
                    when (val logOutStatus = viewModel.logOut()) {
                        is Resource.Success -> {
                            navController.navigate(R.id.action_homeFragment_to_loginFragment)
                        }
                        is Resource.Error -> {
                            Timber.e("why is it throw error? : ${logOutStatus.exceptionMessage}")
                            context?.toast("something went wrong, ${logOutStatus.exceptionMessage} ")
                        }
                        is Resource.Loading ->  Timber.wtf("impossible loading state ")
                    }
                }
                return true
            }
        }
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}