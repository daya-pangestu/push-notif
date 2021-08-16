package com.daya.taha.presentation.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.onNavDestinationSelected
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.model.News
import com.daya.taha.R
import com.daya.taha.databinding.FragmentHomeBinding
import com.daya.taha.utils.EspressoIdlingResource
import com.daya.taha.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel by viewModels<HomeViewModel>()
    private val args by navArgs<HomeFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        toast("logged in as ${if (args.islecturer) "lecturer" else "student"}")

        val newsPagingAdapter = NewsPagingAdapter{ news: News ->
            val navigateDetail = HomeFragmentDirections.actionHomeFragmentToDetailFragment(news)
            findNavController().navigate(navigateDetail)
        }

        newsPagingAdapter.addLoadStateListener { loadState: CombinedLoadStates ->
            if (loadState.refresh is LoadState.Loading) {
                binding.progressBar.isVisible = true
                EspressoIdlingResource.increment()

            } else {
                EspressoIdlingResource.decrement()
                binding.progressBar.isVisible = false

                val errorState = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    context?.toast(it.error.toString())
                }
            }
        }

            binding.rvMain.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = newsPagingAdapter.withLoadStateFooter(
                    footer = NewsPagingLoadingAdapter(newsPagingAdapter::retry)
                )
            }

            lifecycleScope.launch {
                viewModel.infoPagingFlow
                    .collect{
                        newsPagingAdapter.submitData(it)
                    }
            }
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