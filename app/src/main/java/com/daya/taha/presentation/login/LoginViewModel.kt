package com.daya.taha.presentation.login

import androidx.lifecycle.*
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.usecase.LoginWithCredentialUseCase
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

    fun login(credential: String){
        viewModelScope.launch {
            val resStatus = loginWithCredentialUseCase(credential)
            _loginStatus.value = resStatus
        }
    }
}