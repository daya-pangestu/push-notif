package com.daya.taha.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.usecase.InfoPagingUseCase
import com.daya.shared.taha.domain.usecase.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val logOutUseCase: LogOutUseCase,
    private val infoPagingUseCase: InfoPagingUseCase
): ViewModel() {

    suspend fun logOut() : Resource<Unit> {
        return logOutUseCase(Unit)
    }

    private val _infoPagingFlow = infoPagingUseCase()
        .cachedIn(viewModelScope)

    val infoPagingFlow = _infoPagingFlow
}