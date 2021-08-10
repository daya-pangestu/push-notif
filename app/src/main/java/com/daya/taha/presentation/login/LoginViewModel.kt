package com.daya.taha.presentation.login

import androidx.lifecycle.*
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.usecase.LoginWithCredentialUseCase
import com.daya.shared.taha.domain.usecase.SubscribeToTopicUseCase
import com.daya.shared.taha.domain.usecase.SubscribeUserToDefaultTopicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject
constructor(
    private val loginWithCredentialUseCase: LoginWithCredentialUseCase,
    private val subscribeUserToDefaultTopicUseCase: SubscribeUserToDefaultTopicUseCase
) : ViewModel() {

    private val _loginStatus = MutableLiveData<Resource<String>>()
    val loginStatus = _loginStatus

    fun login(credential: String){
        viewModelScope.launch {
            val resStatus = loginWithCredentialUseCase(credential)
            _loginStatus.value = resStatus
        }
    }

    fun subscribingDefaultTopicToCurrentUser() {
        viewModelScope.launch {
            when (val status = subscribeUserToDefaultTopicUseCase(Unit)) {
                is Resource.Loading -> Timber.wtf("impossible loading state")
                is Resource.Success -> Timber.v("succes state")
                is Resource.Error -> Timber.e("error state : ${status.exceptionMessage}")
            }
        }
    }
}