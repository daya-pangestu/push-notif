package com.daya.shared.taha.domain.usecase

import android.net.Uri
import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.di.coroutine.IoDispatcher
import com.daya.shared.taha.domain.model.News
import com.daya.shared.taha.domain.model.NewsNet
import com.daya.shared.taha.domain.repository.IbroadCastRepository
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File
import java.io.InputStream
import javax.inject.Inject

class BroadCastNewsUseCase
@Inject
constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val broadCastRepository: IbroadCastRepository,
) : FlowUseCase<News, Void?>(coroutineDispatcher) {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun execute(param: News): Flow<Resource<Void?>> = callbackFlow<Resource<Void?>>  {
        var newsNet : NewsNet? = NewsNet(
            senderId = param.senderId,
            title = param.title,
            urlAccess = param.urlAccess,
            description = param.description,
            urlImage = param.urlImage,
            status = param.status,
            topics = param.topics.map { it.topicName },
            broadcastRequested = param.broadcastRequested
        )
        val isImageNotExist = newsNet?.urlImage.isNullOrEmpty()

        trySend(Resource.loading("BroadCasting news"))

        //some memory consuming object
        var fileImage: File?
        var streamImageLocal : InputStream?
        var uploadedImageRef : StorageReference?
        var uploadTask : StorageTask<UploadTask.TaskSnapshot>?
        var uriImageCloud : Uri?

        if (isImageNotExist) {

            val casted = broadCastRepository.broadCastNews(newsNet!!)
            val resCasted = Resource.Success(casted)
            trySend(resCasted)
        } else {
            val stringImage = newsNet?.urlImage ?: ""
            fileImage = File(stringImage)
            streamImageLocal = withContext(coroutineContext) {
                broadCastRepository.readFile(stringImage)
            }
            val uploadedName = "${System.currentTimeMillis()}_${fileImage.name}"
            uploadedImageRef = broadCastRepository.addChild(uploadedName)

            uploadTask = uploadedImageRef.putStream(streamImageLocal!!)
                .addOnProgressListener { taskSnapshot ->
                    val progress: Double =
                        100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                    trySend(Resource.loading(progress = "uploading img: $progress"))
                }
                .addOnFailureListener {
                    trySend(Resource.error(it.localizedMessage)) // it should get cought by flowUseCase
                }
                .addOnSuccessListener {
                    trySend(Resource.loading(progress = "image uploaded"))
                }

            uriImageCloud = uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        trySend(Resource.error(it.localizedMessage))
                    }
                }
                uploadedImageRef?.downloadUrl
            }.await()

            if (uriImageCloud == null) trySend(Resource.error(" failed to upload image"))

            val infoWithDownloadUri = newsNet?.apply {
                urlImage = uriImageCloud.toString()
            }

            broadCastRepository.broadCastNewsWithImage(infoWithDownloadUri!!)
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        trySend(Resource.error(task.exception!!.localizedMessage))
                    } else {
                        trySend(Resource.success(task.result))
                    }
                }
        }

        awaitClose {
            newsNet = null
            streamImageLocal = null
            uploadedImageRef = null
            uploadTask = null
            uriImageCloud = null
            fileImage = null
        }
    }
}