package com.daya.shared.taha.domain.usecase

import com.daya.shared.taha.di.coroutine.IoDispatcher
import com.daya.shared.taha.domain.repository.IAuthRepository
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoginWithCredentialUseCase
@Inject
constructor(
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: IAuthRepository,
) :UseCase<String,String>(coroutineDispatcher) {
    override suspend fun execute(param: String) : String {
        return repository.signInWithCredential(param)
    }
}