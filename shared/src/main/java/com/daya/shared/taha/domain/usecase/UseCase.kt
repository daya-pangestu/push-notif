package com.daya.shared.taha.domain.usecase

import com.daya.shared.taha.data.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlin.jvm.Throws

abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(param: P): Resource<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(param).let {
                    Resource.success(it)
                }
            }
        } catch (e: Exception) {
            Resource.error(e.localizedMessage)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(param: P): R
}