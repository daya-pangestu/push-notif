package com.daya.shared.taha.domain.usecase

import com.daya.shared.taha.di.coroutine.IoDispatcher
import com.daya.shared.taha.domain.repository.IAuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class IsUserLoggedInUseCase
@Inject
constructor(
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: IAuthRepository
) : UseCase<Unit,Boolean>(coroutineDispatcher){

    override suspend fun execute(param: Unit): Boolean {
        return repository.isUserLoggedIn()
    }
}