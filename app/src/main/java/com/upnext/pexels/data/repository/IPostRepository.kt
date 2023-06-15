package com.upnext.pexels.data.repository

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.upnext.pexels.common.Storage
import com.upnext.pexels.domain.repository.PostRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class IPostRepository @Inject constructor(
    private val storage: FirebaseStorage
) : PostRepository {
    override suspend fun isFileVideo(url: String): Boolean {
        val storageReference = storage.reference
        val metadata = storageReference.metadata.await()
        val contentType = metadata.contentType
        return contentType?.startsWith("video/") == true
    }
}