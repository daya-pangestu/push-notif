package com.daya.shared.taha.domain.usecase

import com.daya.shared.taha.data.auth.AuthRepository
import com.daya.shared.taha.di.coroutine.IoDispatcher
import com.daya.shared.taha.domain.repository.IAuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LogOutUseCase
@Inject
constructor(
@IoDispatcher coroutineDispatcher: CoroutineDispatcher,
private val authRepository: IAuthRepository
) : UseCase<Unit, Unit>(coroutineDispatcher) {

    override suspend fun execute(param: Unit) {
        return authRepository.logginOutCurrentUser()
    }
}