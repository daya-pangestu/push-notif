package com.daya.shared.taha.domain.usecase

import com.daya.shared.taha.auth.AuthRepository
import com.daya.shared.taha.di.coroutine.IoDispatcher
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoginWithCredentialUseCase
@Inject
constructor(
    private val repository: AuthRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) :UseCase<AuthCredential,Boolean>(coroutineDispatcher) {
    override suspend fun execute(param: AuthCredential): Boolean {
        return repository.signInWithCredential(param)
    }
}