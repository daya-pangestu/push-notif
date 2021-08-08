package com.daya.taha.presentation.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.daya.shared.taha.data.Resource
import com.daya.taha.R
import com.daya.taha.databinding.FragmentLoginBinding
import com.daya.taha.util.toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding<FragmentLoginBinding>()
    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginWithSt3.setOnClickListener {
            val intent = requestGSO(DOMAIN_st3).signInIntent
            loginGso.launch(intent)
        }
        binding.loginWithItt.setOnClickListener {
            val intent = requestGSO(DOMAIN_ITT).signInIntent
            loginGso.launch(intent)
        }
        observeLoginStatus()
    }

    private fun requestGSO(domain: String): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .setHostedDomain(domain)
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(requireContext(),gso)
    }

    private val loginGso = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            // Google Sign In failed, update UI appropriately
            Timber.w("Google sign in failed $e")
            context?.toast("google sign in failed ${e.localizedMessage}")
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        viewModel.login(idToken)
    }

    private fun observeLoginStatus() {
        viewModel.loginStatus.observe(viewLifecycleOwner){

            when (it) {
                is Resource.Loading -> {
                    //impossible status do nothing
                    Timber.wtf("its should be impossible to be in loading state")
                }
                is Resource.Success -> {
                    lifecycleScope.launch {
                        val name = it.data.lowercase()
                        context?.toast("welcome $name", Toast.LENGTH_LONG)
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                }
                is Resource.Error -> {
                    val text = "login failed ${it.exceptionMessage}"
                    context?.toast(text, Toast.LENGTH_LONG)
                    Timber.e(text)
                }
            }
        }
    }

    companion object {
        private const val DOMAIN_ITT = "ittelkom-pwt.ac.id"
        private const val DOMAIN_st3 = "st3telkom.ac.id"
    }
}