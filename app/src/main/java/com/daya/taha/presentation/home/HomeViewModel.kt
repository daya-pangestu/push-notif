package com.daya.taha.presentation.home

import androidx.lifecycle.ViewModel
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.usecase.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val logOutUseCase: LogOutUseCase
): ViewModel() {

    suspend fun logOut() : Resource<Unit> {
        return logOutUseCase(Unit)
    }
}