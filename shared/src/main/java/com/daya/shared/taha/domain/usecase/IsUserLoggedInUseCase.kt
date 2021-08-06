package com.daya.shared.taha.domain.usecase

import com.daya.shared.taha.auth.AuthRepository
import com.daya.shared.taha.di.coroutine.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class IsUserLoggedInUseCase
@Inject
constructor(
    private val repository: AuthRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : UseCase<Unit,Boolean>(coroutineDispatcher){

    override suspend fun execute(param: Unit): Boolean {
        return repository.isUserLoggedIn()
    }
}