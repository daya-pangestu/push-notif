package com.daya.taha.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.daya.shared.taha.domain.usecase.IsUserLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject
constructor(
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase
) : ViewModel() {
    private val _isUserLoggedIn = liveData {
        val resUserLoggedIn = isUserLoggedInUseCase(Unit)
        emit(resUserLoggedIn)
    }
    val isUserLoggedIn = _isUserLoggedIn
}