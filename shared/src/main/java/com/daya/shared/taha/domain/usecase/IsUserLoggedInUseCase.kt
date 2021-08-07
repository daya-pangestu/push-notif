package com.daya.shared.taha.domain.usecase

import com.daya.shared.taha.data.auth.AuthRepository
import com.daya.shared.taha.di.coroutine.IoDispatcher
import com.daya.shared.taha.domain.repository.IAuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class IsUserLoggedInUseCase
@Inject
constructor(
    private val repository: IAuthRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : UseCase<Unit,Boolean>(coroutineDispatcher){

    override suspend fun execute(param: Unit): Boolean {
        return repository.isUserLoggedIn()
    }
}