package com.daya.shared.taha.domain.usecase

import com.daya.shared.taha.di.coroutine.IoDispatcher
import com.daya.shared.taha.domain.repository.ITopicRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SubscribeUserToDefaultTopicUseCase
@Inject
constructor(
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    private val topicRepository: ITopicRepository
) : UseCase<Unit, Unit>(coroutineDispatcher) {

    override suspend fun execute(param: Unit) {
        return topicRepository.subscribeUserToDefaultTopic()
    }
}