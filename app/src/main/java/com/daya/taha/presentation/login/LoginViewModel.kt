package com.daya.taha.presentation.login

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.*
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.usecase.LoginWithCredentialUseCase
import com.daya.shared.taha.domain.usecase.SubscribeUserToDefaultTopicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private var _isLecturer = false
    fun setIsLecturer(domain: String) {
        val isStudent = domain.split("@").first().isDigitsOnly()
        _isLecturer = !isStudent
    }
    fun isLecturer() = _isLecturer

    suspend fun subscribingDefaultTopicToCurrentUser() {
        return when (val status = subscribeUserToDefaultTopicUseCase(Unit)) {
            is Resource.Loading -> Timber.wtf("impossible loading state")
            is Resource.Success -> Timber.v("succes state")
            is Resource.Error -> Timber.e("error state : ${status.exceptionMessage}")
        }
    }
}