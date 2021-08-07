package com.daya.shared.taha.domain.usecase

import com.daya.shared.taha.di.coroutine.IoDispatcher
import com.daya.shared.taha.domain.model.Topic
import com.daya.shared.taha.domain.model.mapNetToGeneral
import com.daya.shared.taha.domain.repository.ITopicRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetDefaultTopicUseCase
@Inject
constructor(
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    private val topicRepository: ITopicRepository
): UseCase<Unit,List<Topic>>(coroutineDispatcher)
{
    override suspend fun execute(param: Unit): List<Topic> {
        val topicNet = topicRepository.getDefaultTopic()
        return topicNet.mapNetToGeneral()
    }
}