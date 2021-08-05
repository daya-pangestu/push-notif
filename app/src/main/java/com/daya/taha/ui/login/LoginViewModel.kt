package com.daya.taha.ui.login

import androidx.lifecycle.*
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.usecase.LoginWithCredentialUseCase
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject
constructor(
    private val loginWithCredentialUseCase: LoginWithCredentialUseCase
) : ViewModel() {

    private val _loginStatus = MutableLiveData<Resource<String>>()
    val loginStatus = _loginStatus

    fun login(credential: AuthCredential){
        viewModelScope.launch {
            val resStatus = loginWithCredentialUseCase(credential)
            _loginStatus.value = resStatus
        }
    }
}